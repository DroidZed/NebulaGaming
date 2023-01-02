package tn.esprit.nebulagaming.viewmodels

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import tn.esprit.authmodule.repos.UserAuthManager
import tn.esprit.nebulagaming.utils.HelperFunctions.getGravatar
import tn.esprit.nebulagaming.utils.HelperFunctions.getImageFromBackend
import tn.esprit.nebulagaming.utils.HelperFunctions.toastMsg
import tn.esprit.roommodule.dao.UserDao
import tn.esprit.roommodule.models.UserAndBookmarks
import tn.esprit.roommodule.models.UserProfile
import javax.inject.Inject

@HiltViewModel
open class UserManipulationViewModel @Inject constructor(
    protected open val authManager: UserAuthManager,
    protected open val userDao: UserDao
) : DefaultViewModel() {

    fun fetchUserInfoFromDb(): UserAndBookmarks? = runBlocking {
        userDao.getUserWithBookmarks(getUserId())
    }

    fun getUserId(): String = authManager.retrieveUserInfoFromStorage()!!.userId

    open fun persistUser(context: Context, user: UserProfile) = runBlocking {
        try {
            user.photo =
                if (user.photo.isNotBlank()) getImageFromBackend(user.photo) else getGravatar(
                    user.email
                )
            userDao.saveUser(user)
        } catch (e: Exception) {
            toastMsg(context, "Error saving your data")
        }
    }
}