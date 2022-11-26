package tn.esprit.authmodule.repos

import tn.esprit.authmodule.utils.UserInfo


interface UserAuthManager {

    fun saveUserInfoToStorage(id: String, role: String, token: String, refresh: String, status: Int)

    fun retrieveUserInfoFromStorage(): UserInfo?

    fun checkIfUserLoggedIn(): Boolean

    fun checkIfUserIsValid(): Boolean

    fun logOutUser()
}