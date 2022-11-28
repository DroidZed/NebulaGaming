package tn.esprit.authmodule.repos

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import tn.esprit.authmodule.utils.UserInfo
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.USER_KEY
import javax.inject.Inject

class UserAuthManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) :
    UserAuthManager {

    private var sharedPrefs: SharedPreferences
    private val gson: Gson = Gson()

    init {
        sharedPrefs = context.getSharedPreferences(APP_PREFS, MODE_PRIVATE)
    }

    override fun retrieveUserInfoFromStorage(): UserInfo? {
        val json = sharedPrefs.getString(USER_KEY, "")
        if (json == "") return null
        return gson.fromJson(json, UserInfo::class.java)
    }
    
    override fun checkIfUserLoggedIn(): Boolean = retrieveUserInfoFromStorage() != null

    override fun checkIfUserIsValid() = retrieveUserInfoFromStorage()?.status != 0

    override fun logOutUser() = sharedPrefs.edit().clear().apply()

    override fun saveUserInfoToStorage(
        id: String,
        role: Int,
        token: String,
        refresh: String,
        status: Int
    ) =
        sharedPrefs
            .edit()
            .putString(
                USER_KEY,
                gson.toJson(
                    UserInfo(
                        userId = id,
                        role = role,
                        token = token,
                        refresh = refresh,
                        status = status
                    )
                )
            )
            .apply()
}