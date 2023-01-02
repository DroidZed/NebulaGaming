package tn.esprit.nebulagaming.fragments


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.AddProductActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on

@AndroidEntryPoint
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

        val products = mutableListOf<Product>(
        )

        productAdapter = ProductAdapter(products)

        productRVMyShop.apply {
            adapter = productAdapter
            layoutManager =
                LinearLayoutManager(view.context)
        }

        addProductBtn.setOnClickListener {
            val activity: FragmentActivity? = activity

            startActivity(Intent(activity, AddProductActivity::class.java))
        }

    }
}