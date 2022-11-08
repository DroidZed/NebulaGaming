package tn.esprit.apimodule.models

data class UserProfileResponse(
    val name: String,
    val photo: String,
    val email: String,
    val phone: Int,
    val role: Int,
    val level: Int,
    val status: Int
)
