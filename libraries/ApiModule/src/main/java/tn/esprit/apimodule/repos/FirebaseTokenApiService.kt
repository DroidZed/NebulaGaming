package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tn.esprit.apimodule.models.TokenReqBody

interface FirebaseTokenApiService {

    companion object {
        const val API_ID = "fcm"
    }

    @POST("$API_ID/push-token")
    suspend fun saveToken(@Body body: TokenReqBody): Response<Unit>
}