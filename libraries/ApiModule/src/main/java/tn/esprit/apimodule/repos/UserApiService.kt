package tn.esprit.apimodule.repos

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*
import tn.esprit.roommodule.models.UserProfile


interface UserApiService {

    companion object {

        private const val API_ID = "users"
    }

    @GET("$API_ID/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<UserProfile>

    @DELETE("$API_ID/deleteuser/{id}")
    suspend fun deleteProfile(@Path("id") id: String): Response<GenericResp>

    @PUT("$API_ID/changePassword/{id}")
    suspend fun changePassword(
        @Path("id") id: String,
        @Body code: AuthReqBody
    ): Response<GenericResp>

    @PUT("$API_ID/updateProfile/{id}")
    suspend fun updateProfile(
        @Path("id") id: String,
        @Body phoneName: UpdateProfileBody
    ): Response<GenericResp>

    @Multipart
    @PATCH("$API_ID/changeImage/{id}")
    suspend fun updateUserPicture(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Response<GenericResp>

    @POST("$API_ID/requestChangePwd")
    suspend fun requestChangePwd(@Body email: AuthReqBody): Response<GenericResp>
}