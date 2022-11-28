package tn.esprit.apimodule

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.apimodule.repos.ArticleApiService
import tn.esprit.apimodule.repos.AuthApiService
import tn.esprit.apimodule.repos.JobOffreApiService
import tn.esprit.apimodule.repos.UserApiService
import tn.esprit.apimodule.utils.AuthInterceptor
import tn.esprit.apimodule.utils.TokenAuthenticator
import tn.esprit.shared.Consts.FUNCTION_URL


class NetworkClient(private val context: Context) {


    private val secureClient by lazy {
        Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(secureHttpInterceptor())
            .baseUrl(FUNCTION_URL)
            .build()
    }

    private val defaultClient by lazy {

        Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(defaultInterceptor())
            .baseUrl(FUNCTION_URL)
            .build()
    }

    fun getAuthService(): AuthApiService =
        defaultClient.create(AuthApiService::class.java)

    fun getUserService(): UserApiService =
        secureClient.create(UserApiService::class.java)

    fun getArticleService(): ArticleApiService = secureClient.create(ArticleApiService::class.java)

    fun getOffreService():JobOffreApiService=secureClient.create(JobOffreApiService::class.java)

    /**
     * Initialize OkhttpClient with token authenticator
     */
    private fun secureHttpInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .authenticator(TokenAuthenticator(context))
        .build()


    /**
     * Initialize OkhttpClient with a default interceptor
     */
    private fun defaultInterceptor(): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}