package tn.esprit.nebulagaming.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import tn.esprit.apimodule.models.Product
import tn.esprit.nebulagaming.AddProductActivity
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.adapters.MyProductAdapter
import tn.esprit.nebulagaming.adapters.ProductAdapter
import tn.esprit.nebulagaming.utils.HelperFunctions
import tn.esprit.nebulagaming.utils.Status
import tn.esprit.nebulagaming.utils.hideKeyboard
import tn.esprit.nebulagaming.utils.on
import tn.esprit.nebulagaming.viewmodels.ProductViewModel

@AndroidEntryPoint
class MyShopFragment : Fragment(R.layout.fragment_my_shop) {

    private lateinit var MyProductAdapter: MyProductAdapter
    private val ProductViewModel: ProductViewModel by viewModels()
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

        MyProductAdapter = MyProductAdapter(products)

        productRVMyShop.apply {
            adapter = MyProductAdapter
            layoutManager =
                LinearLayoutManager(view.context)
        }
        loadData()
        addProductBtn.setOnClickListener {
            val activity: FragmentActivity? = activity

            startActivity(Intent(activity, AddProductActivity::class.java))
        }

    }


    private fun loadData(){
        ProductViewModel.loadMyProducts(view?.context!!).observe(requireActivity()){
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            MyProductAdapter.addAll(this)
                        }
                    }
                    Status.LOADING -> {
                        Log.d("TAG", "loadData: LOADING")
                    }
                    Status.ERROR -> {
                        HelperFunctions.toastMsg(view?.context!!, it.message!!)
                        Log.e("Myshop-JOB", it.message)
                    }
                }
            }
        }

    }
}