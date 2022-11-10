package tn.esprit.apimodule.repos

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import tn.esprit.apimodule.models.AuthReqBody
import tn.esprit.apimodule.models.AuthResp
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.LoginReqBody

interface AuthApiService {

    @POST("login")
    suspend fun login(@Body emailPassword: LoginReqBody): Response<AuthResp>

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