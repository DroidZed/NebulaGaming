package tn.esprit.apimodule.repos

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*

interface AuthApiService {

    @POST("login")
    suspend fun login(@Body emailPassword: LoginReqBody): Response<AuthResp>

    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun register(@Body emailPasswordinfo: UserRegister): Response<RegisterReqBody>

    @POST("forgetPassword")
    suspend fun forgetPassword(@Body email: AuthReqBody): Response<GenericResp>

    @POST("resetVerif")
    suspend fun resetVerifCode(@Body email: AuthReqBody): Response<GenericResp>

    @POST("verifyAccount")
    suspend fun accountVerificationWithGencode(@Body codeEmail: AuthReqBody): Response<GenericResp>

    @POST("forgetPasswordReq")
    suspend fun forgetPasswordRequest(@Body email: AuthReqBody): Response<GenericResp>

    @POST("forgetPassword")
    suspend fun unsecureChangePassword(@Body emailCodePassword: AuthReqBody): Response<GenericResp>

    @POST("refresh-token")
    @FormUrlEncoded
    fun resetUserToken(@Header("refreshToken") refreshToken: String): Call<Response<AuthResp>>
}