package tn.esprit.apimodule.models

data class TokenReqBody(
    val token: String,
    val newToken: String? = null
)