package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authManager: UserAuthManager
) : ViewModel() {

    fun checkIfCompany(): Boolean = authManager.retrieveUserInfoFromStorage()!!.role == 2

    fun handleLogOut() {
        authManager.logOutUser()
    }

}