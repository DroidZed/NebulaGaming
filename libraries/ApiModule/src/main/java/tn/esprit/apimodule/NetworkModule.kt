package tn.esprit.apimodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesSecureRetrofit(token: String): Retrofit {
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(AuthInterceptor(token))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        return Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(FUNCTION_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        return Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(FUNCTION_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesAuthAPI(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)
}