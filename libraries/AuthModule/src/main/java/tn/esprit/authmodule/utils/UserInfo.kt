package tn.esprit.authmodule.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val userId: String,
    var refresh: String,
    var token: String,
    var role: String,
    var status: Int
) : Parcelable