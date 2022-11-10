package tn.esprit.authmodule.repos

import com.auth0.android.jwt.JWT
import javax.inject.Inject


class JWTManagerImpl @Inject constructor() : JWTManager {

    override fun extractUserIdFromJWT(token: String): String =

        JWT(token).getClaim("id").asString()!!
}