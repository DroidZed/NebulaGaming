package tn.esprit.authmodule.repos

import tn.esprit.shared.UserInfo

interface UserAuthManager {

    fun saveUserInfoToStorage(id: String, role: String, token: String, refresh: String)

    fun retrieveUserInfoFromStorage(): UserInfo

    fun checkIfUserLoggedIn(): Boolean

    fun logOutUser()
}