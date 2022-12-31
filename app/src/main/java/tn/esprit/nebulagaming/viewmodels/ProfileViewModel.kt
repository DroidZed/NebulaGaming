package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.UpdateProfileBody
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.nebulagaming.utils.Resource
import tn.esprit.roommodule.dao.UserDao
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    override val authManager: UserAuthManager,
    override val userDao: UserDao
) : UserManipulationViewModel(authManager, userDao) {

    val nameUser = MutableLiveData("")

    fun handleUpdate(context: Context, name: String, phone: String) {

        if (listOf(name, phone).any { it.isBlank() })
            toastMsg(context, "Verify your input!")
        else
            updateUserDetails(context, name, phone)
    }

    fun changeProfilePhoto(context: Context, file: File) {

        val id = authManager.retrieveUserInfoFromStorage()!!.userId

        val client = NetworkClient(context)

        val userService = client.getUserService()

        val requestBody: RequestBody =
            RequestBody.create("image/${file.extension}".toMediaTypeOrNull(), file)

        val fileToUpload: MultipartBody.Part =
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestBody
            )

        job = CoroutineScope(Dispatchers.IO).launch {

            val response = userService.updateUserPicture(id, fileToUpload)
            val user = userDao.getUserWithBookmarks(id)!!.user
            val oldPhoto = user!!.photo

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        try {
                            user.photo = response.body()!!.message!!
                            userDao.update(user)
                            onSuccess("Photo updated successfully!!")
                        } catch (e: Exception) {
                            user.photo = oldPhoto
                        }
                    } else
                        onError(response)
                } catch (e: Exception) {
                    super.onError()
                }
            }
        }
    }

    private fun updateUserDetails(context: Context, name: String, phone: String) {

        val id = authManager.retrieveUserInfoFromStorage()!!.userId

        val client = NetworkClient(context)

        val userService = client.getUserService()

        job = CoroutineScope(Dispatchers.IO).launch {

            val response =
                userService.updateProfile(id, UpdateProfileBody(name = name, phone = phone))

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val user = userDao.getUserWithBookmarks(id)!!.user
                        user?.apply {
                            this.phone = phone
                            this.name = name
                        }
                        userDao.update(user!!)
                        onSuccess(response.body()!!.message)
                    } else
                        onError(response)
                } catch (e: Exception) {
                    super.onError()
                }
            }
        }

    }

    fun loadUser(context: Context) = liveData(Dispatchers.IO) {

        val client = NetworkClient(context)

        val userServices = client.getAdminService()
        emit(Resource.loading(data = null))
        try {
            val response = userServices.getAllUsers()
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


    fun enableUserById(context: Context, id: String) = liveData(Dispatchers.IO) {
        val client = NetworkClient(context)
        val userServices = client.getAdminService()
        emit(Resource.loading(data = null))
        try {
            val response = userServices.enableUser(id)
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

    fun disableUserById(context: Context, id: String) = liveData(Dispatchers.IO) {
        val client = NetworkClient(context)
        val userServices = client.getAdminService()
        emit(Resource.loading(data = null))
        try {
            val response = userServices.disableUser(id)
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