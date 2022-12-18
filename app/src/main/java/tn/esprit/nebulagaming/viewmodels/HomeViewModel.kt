package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.apimodule.NetworkClient
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.utils.ResponseConverter
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.utils.Resource
import tn.esprit.roommodule.dao.UserDao
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    override val authManager: UserAuthManager,
    override val userDao: UserDao
) : UserManipulationViewModel(authManager, userDao) {

    fun checkIfCompany(): Boolean = authManager.retrieveUserInfoFromStorage()!!.role == 2

    fun handleLogOut() = authManager.logOutUser()

    fun fetchUserInfo(context: Context) = liveData {

        val netClient = NetworkClient(context)

        val userService = netClient.getUserService()

        emit(Resource.loading(data = null))
        try {
            val response =
                userService.getProfile(getUserId())
            if (response.isSuccessful)
                emit(Resource.success(response.body()))
            else
                emit(
                    Resource.error(
                        null,
                        ResponseConverter.convert<GenericResp>(
                            response.errorBody()!!.string()
                        ).data?.error!!
                    )
                )

        } catch (ex: Exception) {
            emit(
                Resource.error(
                    null,
                    ex.message
                        ?: "Error loading user data..."
                )
            )
        }
    }
}