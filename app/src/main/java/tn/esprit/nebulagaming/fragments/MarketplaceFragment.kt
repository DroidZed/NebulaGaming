package tn.esprit.nebulagaming.fragments

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.data.ProductDetails
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on


class MarketplaceFragment : Fragment(R.layout.fragment_marketplace) {

    private lateinit var productAdapter: ProductAdapter

    private lateinit var searchProductsBar: EditText
    private lateinit var categoriesGroup: ChipGroup
    private lateinit var productsListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchProductsBar = view.findViewById(R.id.searchProductsBar)
        categoriesGroup = view.findViewById(R.id.categoriesGroup)
        productsListRV = view.findViewById(R.id.productsListRV)

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


        val products = mutableListOf(
            ProductDetails(
                id = "a56f4sd",
                name = "Product 1",
                desc = resources.getString(R.string.lorem),
                image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
            ),
            ProductDetails(
                id = "a56f4se456rfsd",
                name = "Product 2",
                desc = resources.getString(R.string.lorem),
                image = "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
            )
        )

        productAdapter = ProductAdapter(products)

        productsListRV.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}