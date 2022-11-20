package tn.esprit.authmodule.repos

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import tn.esprit.shared.Consts.APP_PREFS
import tn.esprit.shared.Consts.JWT_KEY
import tn.esprit.shared.Consts.REFRESH_KEY
import tn.esprit.shared.Consts.ROLE_KEY
import tn.esprit.shared.Consts.STATUS_KEY
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
            sharedPrefs.getString(REFRESH_KEY, "")!!,
            sharedPrefs.getString(ROLE_KEY, "")!!,
            sharedPrefs.getInt(STATUS_KEY, 0)
        )

    override fun checkIfUserLoggedIn(): Boolean = sharedPrefs.contains(U_ID_KEY);

    override fun checkIfUserIsValid() = sharedPrefs.getInt(STATUS_KEY, 0) != 0

    override fun logOutUser() = sharedPrefs.edit().clear().apply()

    override fun saveUserInfoToStorage(
        id: String,
        role: String,
        token: String,
        refresh: String,
        status: Int
    ) =
        sharedPrefs
            .edit()
            .putString(U_ID_KEY, id)
            .putString(JWT_KEY, token)
            .putString(REFRESH_KEY, refresh)
            .putString(ROLE_KEY, role)
            .putInt(STATUS_KEY, status)
            .apply()

}