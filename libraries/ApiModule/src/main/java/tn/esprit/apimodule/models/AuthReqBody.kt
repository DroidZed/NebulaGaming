package tn.esprit.apimodule.models

data class AuthReqBody(
    val email: String? = null,
    val code: String? = null,
    val password: String? = null
)
