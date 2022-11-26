package tn.esprit.nebulagaming.fragments


import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.data.ProductDetails
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on


class MyShopFragment : Fragment(R.layout.fragment_my_shop) {

    private lateinit var productAdapter: ProductAdapter

    private lateinit var searchMyShop: EditText
    private lateinit var productRVMyShop: RecyclerView
    private lateinit var addProductBtn: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMyShop = view.findViewById(R.id.searchMyShop)
        productRVMyShop = view.findViewById(R.id.productRVMyShop)
        addProductBtn = view.findViewById(R.id.addProductBtn)

        searchMyShop.on(EditorInfo.IME_ACTION_DONE) {
            searchMyShop.apply {
                clearFocus()
                hideKeyboard()
            }
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

        productRVMyShop.apply {
            adapter = productAdapter
            layoutManager =
                LinearLayoutManager(view.context)
        }

    }
}