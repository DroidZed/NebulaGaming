package tn.esprit.apimodule.models

data class AuthResp(
    val error: String? = null,
    val token: String? = null,
    val refresh: String? = null
)