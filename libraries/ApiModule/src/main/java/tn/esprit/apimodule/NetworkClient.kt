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
import java.util.concurrent.TimeUnit


class NetworkClient {

    private lateinit var authService: AuthApiService
    private lateinit var userService: UserApiService

    fun getAuthService(context: Context): AuthApiService {

        if (!::authService.isInitialized) {

            val retrofit = Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .baseUrl(FUNCTION_URL)
                .build()

            authService = retrofit.create(AuthApiService::class.java)
        }

        return authService
    }

    fun getUserService(context: Context): UserApiService {

        if (!::userService.isInitialized) {

            val retrofit = Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultInterceptor())
                .baseUrl(FUNCTION_URL)
                .build()

            userService = retrofit.create(UserApiService::class.java)
        }

        return userService

    }


    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    /**
     * Initialize OkhttpClient with a default interceptor
     */
    private fun defaultInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}