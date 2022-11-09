package tn.esprit.authmodule.repos

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.JWT_KEY
import tn.esprit.shared.Consts.REFRESH_KEY
import tn.esprit.shared.Consts.U_ID_KEY
import tn.esprit.shared.UserInfo
import javax.inject.Inject

class UserAuthManagerImpl @Inject constructor(@ApplicationContext context: Context) :
    UserAuthManager {

    private var sharedPrefs: SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences(APP_PREFS, MODE_PRIVATE)
    }

    override fun retrieveUserInfoFromStorage(): UserInfo =
        UserInfo(
            sharedPrefs.getString(U_ID_KEY, "")!!,
            sharedPrefs.getString(JWT_KEY, "")!!,
            sharedPrefs.getString(
                REFRESH_KEY, ""
            )!!
        )

    override fun logOutUser() {
        sharedPrefs.edit().clear().apply()
    }


    override fun saveUserInfoToStorage(id: String, token: String, refresh: String) {
        sharedPrefs.edit().putBoolean("REM", true).putString(U_ID_KEY, id).putString(JWT_KEY, token)
            .putString(REFRESH_KEY, refresh).apply()
    }
}