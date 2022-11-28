package tn.esprit.authmodule.repos

interface JWTManager {

    fun extractUserIdFromJWT(token: String): String

    fun extractRoleFromJWT(token: String): Int

    fun extractStatusFromJWT(token: String): Int
}