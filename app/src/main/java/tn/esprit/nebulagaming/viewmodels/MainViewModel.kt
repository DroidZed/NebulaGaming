package tn.esprit.nebulagaming.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.authmodule.repos.UserAuthManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authManager: UserAuthManager
) : ViewModel() {


    fun isUserLoggedIn(): Boolean {
        return authManager.checkIfUserLoggedIn()
    }
}