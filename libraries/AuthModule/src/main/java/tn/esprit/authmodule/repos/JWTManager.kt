package tn.esprit.authmodule.repos

interface JWTManager {

    fun extractUserIdFromJWT(token: String): String
}