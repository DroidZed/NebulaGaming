package tn.esprit.apimodule.repos

import android.media.Image
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*


interface UserApiService {

    @GET("users/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<UserProfileResponse>

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
    suspend fun updateUserPicture(@Part("image") image: Image): Response<GenericResp>

    @POST("users/requestChangePwd")
    suspend fun requestChangePwd(@Body email: AuthReqBody): Response<GenericResp>

    @PATCH("users/changeLevel/{id}")
    suspend fun changeLevel(@Body level: UpdateLevelQuery, @Path("id") id: String): Response<GenericResp>
}