package tn.esprit.apimodule.models

data class UserRegister(
    val name: String,
    val email: String,
    val password:String,
    val phone: Int,
    val role: Int,

)
