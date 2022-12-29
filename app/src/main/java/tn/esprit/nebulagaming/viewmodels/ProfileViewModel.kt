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
import tn.esprit.nebulagaming.viewholders.ListUsersViewHolder
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {

    fun loadUser(context: Context) =
        liveData(Dispatchers.IO) {

            val client = NetworkClient(context)

            val userServices = client.getAdminService()
            emit(Resource.loading(data = null))
            try {
                val response= userServices.getAllUsers()
                if (response.isSuccessful)
                    emit(
                        Resource.success(
                            data = response.body()
                        )
                    )
                else
                    emit(
                        Resource.error(
                            data = null,
                            message = ResponseConverter.convert<GenericResp>(
                                response.errorBody()!!.string()
                            ).data?.error!!
                        )
                    )
            } catch (ex: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = ex.message
                            ?: "Unable to retrieve articles at the moment, please try again later."
                    )
                )
            }
            }



    fun EnableuserId(context: Context, id: String) = liveData( Dispatchers.IO ){
        val client = NetworkClient(context)
        val userServices = client.getAdminService()
        emit(Resource.loading(data = null))
        try {
            val response= userServices.enableUser(id)
            if (response.isSuccessful)
                emit(
                    Resource.success(
                        data = response.body()
                    )
                )
            else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
        } catch (ex: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = ex.message
                        ?: "Unable to retrieve articles at the moment, please try again later."
                )
            )
        }
    }

    fun DisableuserId(context: Context, id: String) = liveData( Dispatchers.IO ){
        val client = NetworkClient(context)
        val userServices = client.getAdminService()
        emit(Resource.loading(data = null))
        try {
            val response= userServices.disableUser(id)
            if (response.isSuccessful)
                emit(
                    Resource.success(
                        data = response.body()
                    )
                )
            else
                emit(
                    Resource.error(
                        data = null,
                        message = ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )
        } catch (ex: Exception) {
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
