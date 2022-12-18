package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.Category
import tn.esprit.apimodule.models.Product
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(
    private val userAuthManager: UserAuthManager,
) : DefaultViewModel() {

    val productsData = MutableLiveData<List<Product>>()

    val categories = MutableLiveData<List<Category>>()

    fun getCategories(context: Context) {

        val client = NetworkClient(context)

        val service = client.getMarketplaceService()


    }

    fun getMyProducts(context: Context) {

        val client = NetworkClient(context)

        val service = client.getMarketplaceService()



    }

    fun getProducts(context: Context) {

        val client = NetworkClient(context)

        val service = client.getMarketplaceService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val resp = service.getAllProducts()

            withContext(Dispatchers.Main) {
                try {
                    if (resp.isSuccessful)
                        productsData.postValue(resp.body())
                    else
                        onError(resp)
                } catch (e: Exception) {
                    super.onError()
                }
            }
        }
    }
}