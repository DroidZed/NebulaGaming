package tn.esprit.authmodule.repos

import tn.esprit.shared.UserInfo

interface UserAuthManager {

    fun saveUserInfoToStorage(id: String, role: String, token: String, refresh: String, status: Int)

    fun retrieveUserInfoFromStorage(): UserInfo

    fun checkIfUserLoggedIn(): Boolean

    fun checkIfUserIsValid(): Boolean

    fun logOutUser()
}