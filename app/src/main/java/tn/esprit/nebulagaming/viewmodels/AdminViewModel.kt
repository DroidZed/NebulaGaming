package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.nebulagaming.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(): ViewModel(){

    fun loadCountUsers(context: Context)= liveData(Dispatchers.IO)  {

        val client = NetworkClient(context)
        val responseAdmin = client.getAdminService()
        emit(Resource.loading(data = null))

        try{
                val response = responseAdmin.getCountAllUsers()
            if (response.isSuccessful)
                emit(
                    Resource.success(
                        data = response.body(),

                        )
                ) else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
        }catch (ex: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = ex.message
                        ?: "Unable to retrieve articles at the moment, please try again later."
                )
            )
        }

    }


    fun loadEnableDisableAccount(context: Context)= liveData(Dispatchers.IO) {
        val client = NetworkClient(context)
        val responseAdmin = client.getAdminService()
        emit(Resource.loading(data = null))

        try{
            val response = responseAdmin.getCountAllEnableDiscableCount()
            if (response.isSuccessful)
                emit(
                    Resource.success(
                        data = response.body(),

                        )
                ) else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
        }catch (ex: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = ex.message
                        ?: "Unable to retrieve articles at the moment, please try again later."
                )
            )
        }

    }

    fun loadCategProducts(context: Context)=liveData(Dispatchers.IO){
        val client = NetworkClient(context)
        val responseAdmin = client.getAdminService()
        emit(Resource.loading(data = null))

        try{
            val response = responseAdmin.getAllMethodProducts()
            if (response.isSuccessful)
                emit(
                    Resource.success(
                        data = response.body(),

                        )
                ) else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
        }catch (ex: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = ex.message
                        ?: "Unable to retrieve articles at the moment, please try again later."
                )
            )
        }

    }

}