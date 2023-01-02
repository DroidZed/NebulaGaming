package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(
    private val userAuthManager: UserAuthManager,
) : DefaultViewModel() {


    fun getCategories(context: Context) = liveData {

        emit(Resource.loading(data = null))

        try {
            val client = NetworkClient(context)

            val service = client.getCategoryService()

            val resp = service.getAllCategories()

            if (resp.isSuccessful) emit(Resource.success(resp.body()))
            else emit(
                Resource.error(
                    data = null,
                    message = ResponseConverter.convert<GenericResp>(
                        resp.errorBody()!!.string()
                    ).data?.error!!
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message!!))
        }
    }

    fun getMyProducts(context: Context) = liveData {

        emit(Resource.loading(data = null))

        try {
            val client = NetworkClient(context)

            val service = client.getMarketplaceService()

            val resp = service.getMyProducts(userAuthManager.retrieveUserInfoFromStorage()!!.userId)

            if (resp.isSuccessful) emit(Resource.success(resp.body()))
            else emit(
                Resource.error(
                    data = null,
                    message = ResponseConverter.convert<GenericResp>(
                        resp.errorBody()!!.string()
                    ).data?.error!!
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message!!))
        }
    }

    fun getProducts(context: Context, page: Int) = liveData {

        emit(Resource.loading(data = null))

        try {
            val client = NetworkClient(context)

            val service = client.getMarketplaceService()

            val resp = service.getAllProducts(page)

            if (resp.isSuccessful) emit(Resource.success(resp.body()))
            else emit(
                Resource.error(
                    data = null,
                    message = ResponseConverter.convert<GenericResp>(
                        resp.errorBody()!!.string()
                    ).data?.error!!
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message!!))
        }
    }

    fun getProductById(context: Context, id: String) = liveData {

        emit(Resource.loading(data = null))

        try {
            val client = NetworkClient(context)

            val service = client.getMarketplaceService()

            val resp = service.getOneProduct(id)

            if (resp.isSuccessful) emit(Resource.success(resp.body()))
            else emit(
                Resource.error(
                    data = null,
                    message = ResponseConverter.convert<GenericResp>(
                        resp.errorBody()!!.string()
                    ).data?.error!!
                )
            )
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message!!))
        }
    }
}