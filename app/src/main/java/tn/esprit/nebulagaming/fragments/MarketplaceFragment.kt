package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.MarketplaceViewModel

@AndroidEntryPoint
class MarketplaceFragment : Fragment(R.layout.fragment_marketplace) {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var filterProductsBtn: FloatingActionButton
    private lateinit var searchProductsBar: EditText
    private lateinit var categoriesGroup: ChipGroup
    private lateinit var productsListRV: RecyclerView
    private lateinit var refreshLayoutMarketplace: SwipeRefreshLayout
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private var pageNumber: Int = 1
    private var pagedItemsCount: Int = 0
    private var totalPages: Int = 0

    private val marketPlaceVM: MarketplaceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchProductsBar = view.findViewById(R.id.searchProductsBar)
        categoriesGroup = view.findViewById(R.id.categoriesGroup)
        productsListRV = view.findViewById(R.id.productsListRV)
        filterProductsBtn = view.findViewById(R.id.filterProductsBtn)
        refreshLayoutMarketplace = view.findViewById(R.id.refreshLayoutMarketplace)

        searchProductsBar.on(IME_ACTION_DONE) {
            searchProductsBar.apply {
                clearFocus()
                hideKeyboard()
                productAdapter.filterProductsByName(searchProductsBar.text.toString())
            }
        }

        filterProductsBtn.setOnClickListener {}

        productAdapter = ProductAdapter(mutableListOf())

        productsListRV.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        setupUi()

        categoriesGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            productAdapter.filterProductsByCategory(
                group.findViewById<Chip>(checkedIds[0]).text.toString()
            )
        }

        refreshLayoutMarketplace.setOnRefreshListener {
            productAdapter.clear()
            loadProducts(pageNumber)
            productsListRV.refreshDrawableState()
        }

        // Configure the refreshing colors
        refreshLayoutMarketplace.setColorSchemeResources(
            R.color.purple_500,
            R.color.DarkPurple,
        )

        setRecyclerViewScrollListener()
    }

    private fun setupUi() {

        loadCategories()

        loadProducts(pageNumber)
    }

    private fun setRecyclerViewScrollListener() {

        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (
                    linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.layoutManager?.itemCount?.minus(
                        1
                    )
                    &&
                    pageNumber < totalPages
                ) {
                    pageNumber++
                    loadProducts(pageNumber, false)
                }
            }
        }

        productsListRV.addOnScrollListener(scrollListener)
    }

    private fun loadCategories() {
        marketPlaceVM.getCategories(requireContext()).observe(requireActivity()) {

            it.let { rs ->

                when (rs.status) {

                    Status.LOADING -> {}

                    Status.SUCCESS -> {
                        rs.data.let { categories ->

                            categories?.forEach {

                                val chip = Chip(requireContext())

                                chip.apply { text = it.name }

                                categoriesGroup.addView(chip)
                            }
                        }
                    }

                    Status.ERROR -> {
                        toastMsg(
                            requireContext(),
                            "Unable to load categories for the moment, please try again later."
                        )
                    }
                }
            }
        }
    }

    private fun loadProducts(page: Int, initialRun: Boolean? = true) {
        marketPlaceVM.getProducts(view?.context!!, page).observe(requireActivity()) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            pagedItemsCount = this.pageSize
                            pageNumber = this.page
                            totalPages = this.pages
                            productAdapter.addAll(this.products)
                            if (initialRun == true) refreshLayoutMarketplace.isRefreshing = false
                            productsListRV.refreshDrawableState()
                        }
                    }
                    Status.LOADING -> {
                        if (initialRun == true) refreshLayoutMarketplace.isRefreshing = true
                    }
                    Status.ERROR -> {
                        if (initialRun == true)
                            refreshLayoutMarketplace.isRefreshing = false
                        toastMsg(requireContext(), "Unable to load products at the moment...")
                    }
                }
            }
        }
    }
}