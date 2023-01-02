package tn.esprit.nebulagaming.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.AddProductActivity
import tn.esprit.nebulagaming.ProductDetailsActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.MarketplaceViewModel

@AndroidEntryPoint
class MarketplaceFragment : Fragment(R.layout.fragment_marketplace) {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var addProductBtn: FloatingActionButton
    private lateinit var searchProductsBar: EditText
    private lateinit var categoriesGroup: ChipGroup
    private lateinit var productsListRV: RecyclerView

    private val marketPlaceVM: MarketplaceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchProductsBar = view.findViewById(R.id.searchProductsBar)
        categoriesGroup = view.findViewById(R.id.categoriesGroup)
        productsListRV = view.findViewById(R.id.productsListRV)
        addProductBtn = view.findViewById(R.id.addProductBtn)

        searchProductsBar.on(IME_ACTION_DONE) {
            searchProductsBar.apply {
                clearFocus()
                hideKeyboard()
            }
        }

        val categories = mutableListOf("CPUs", "MOBOs", "GPUs", "Monitors", "Mice", "Keyboards")

        categories.forEach {
            val chip = Chip(view.context)
            chip.apply {
                text = it
            }
            categoriesGroup.addView(chip)
        }

        val products = mutableListOf<Product>()

        searchProductsBar.on(IME_ACTION_DONE) {
            searchProductsBar.clearFocus()
            searchProductsBar.hideKeyboard()

            val filtered = products.asSequence().filter { p ->
                p.name == searchProductsBar.text.toString()
            }.toMutableList()
            productAdapter.apply {
                clear()
                addAll(filtered)
            }
        }

        addProductBtn.setOnClickListener {






        }

        productAdapter = ProductAdapter(products)

        productsListRV.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}