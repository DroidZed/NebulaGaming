package tn.esprit.apimodule

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.apimodule.repos.AuthApiService
import tn.esprit.apimodule.repos.UserApiService
import tn.esprit.apimodule.utils.AuthInterceptor
import tn.esprit.shared.Consts.FUNCTION_URL


class NetworkClient(context: Context) {

    private val secureClient: Retrofit
    private val defaultClient: Retrofit


    init {
        secureClient = Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context))
            .baseUrl(FUNCTION_URL)
            .build()

        defaultClient = Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(defaultInterceptor())
            .baseUrl(FUNCTION_URL)
            .build()
    }

    fun getAuthService(): AuthApiService =
        defaultClient.create(AuthApiService::class.java)

    fun getUserService(): UserApiService =
        secureClient.create(UserApiService::class.java)


    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .build()


    /**
     * Initialize OkhttpClient with a default interceptor
     */
    private fun defaultInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}