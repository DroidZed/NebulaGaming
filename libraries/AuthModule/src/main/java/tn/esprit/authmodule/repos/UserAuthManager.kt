package tn.esprit.authmodule.repos

import tn.esprit.shared.UserInfo

interface UserAuthManager {

    fun saveUserInfoToStorage(id: String, token: String)

    fun retrieveUserInfoFromStorage(): UserInfo
}