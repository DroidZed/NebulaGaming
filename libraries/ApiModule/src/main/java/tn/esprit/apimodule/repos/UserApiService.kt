package tn.esprit.apimodule.repos

import android.media.Image
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*
import tn.esprit.roommodule.models.UserProfile


interface UserApiService {

    @GET("users/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<tn.esprit.roommodule.models.UserProfile>

    @DELETE("users/deleteuser/{id}")
    suspend fun deleteProfile(@Path("id") id: String): Response<GenericResp>

    @PUT("users/updateProfile/{id}")
    suspend fun updateProfile(@Path("id") id: String): Response<GenericResp>

    @PUT("users/changePassword/{id}")
    suspend fun changePassword(@Path("id") id: String, @Body code: AuthReqBody): Response<GenericResp>

    @PUT("users/updateProfile/{id}")
    suspend fun updateProfile(@Path("id") id: String, @Body phoneName: UpdateProfileBody): Response<GenericResp>

    @Multipart
    @PATCH("users/changeImage/{id}")
    suspend fun updateUserPicture(@Part image: Image): Response<GenericResp>

    @POST("users/requestChangePwd")
    suspend fun requestChangePwd(@Body email: AuthReqBody): Response<GenericResp>

    @PATCH("users/changeLevel/{id}")
    suspend fun changeLevel(@Body level: UpdateLevelQuery, @Path("id") id: String): Response<GenericResp>
}