package tn.esprit.shared

data class UserInfo(
    val userId: String,
    var refresh: String,
    var token: String,
    var role: String,
    var status: Int
)