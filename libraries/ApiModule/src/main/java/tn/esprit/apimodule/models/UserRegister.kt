package tn.esprit.apimodule.models

data class UserRegister(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: Int,
)
