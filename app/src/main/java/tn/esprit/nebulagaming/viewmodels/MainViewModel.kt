package tn.esprit.nebulagaming.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import tn.esprit.authmodule.repos.JWTManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val JwtManager: JWTManager) : DefaultViewModel() {

    fun checkIfAdmin() =
        JwtManager.extractRoleFromJWT(authManager.retrieveUserInfoFromStorage()!!.token) == 0


    fun isUserLoggedIn(): Boolean {
        return authManager.checkIfUserLoggedIn()
    }
}