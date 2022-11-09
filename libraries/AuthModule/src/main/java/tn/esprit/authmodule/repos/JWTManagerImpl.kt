package tn.esprit.authmodule.repos


import android.util.Base64
import com.google.gson.Gson
import javax.inject.Inject

data class Payload(
    val id: String,
    val iat: String,
    val exp: String
)

class JWTManagerImpl @Inject constructor() : JWTManager {

    private val gson = Gson()

    override fun extractUserIdFromJWT(token: String): String {

        val chunks = token.split("\\.")

        val strPayload = String(Base64.decode(chunks[1], Base64.DEFAULT))

        val deserializedPayload = gson.fromJson(strPayload, Payload::class.java)

        return deserializedPayload.id
    }
}