package tn.esprit.apimodule

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.apimodule.repos.*
import tn.esprit.apimodule.utils.AuthInterceptor
import tn.esprit.apimodule.utils.TokenAuthenticator
import tn.esprit.shared.Consts.FUNCTION_URL
import java.util.concurrent.TimeUnit


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

    fun retrieveSecureOkHttpClientInstance() = secureHttpInterceptor()

    fun getUserService(): UserApiService =
        secureClient.create(UserApiService::class.java)

    fun getArticleService(): ArticleApiService = secureClient.create(ArticleApiService::class.java)

    fun getEventService(): EventApiService = secureClient.create(EventApiService::class.java)

    fun getCategoryService(): CategoryApiService =
        secureClient.create(CategoryApiService::class.java)

    fun getProductService(): ProductApiService = secureClient.create(ProductApiService::class.java)

    fun getOffreService(): JobOfferApiService = secureClient.create(JobOfferApiService::class.java)

    /**
     * Initialize OkhttpClient with token authenticator
     */
    private fun secureHttpInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .authenticator(TokenAuthenticator(context))
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .callTimeout(5, TimeUnit.SECONDS)
        .build()


    /**
     * Initialize OkhttpClient with a default interceptor
     */
    private fun defaultInterceptor(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .build()
}