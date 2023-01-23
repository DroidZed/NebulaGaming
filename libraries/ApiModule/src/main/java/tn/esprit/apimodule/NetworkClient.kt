package tn.esprit.apimodule

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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


    private inline fun <reified T> getService(client: Retrofit): T = client.create(T::class.java)


    fun getAuthService() = getService<AuthApiService>(defaultClient)

    fun getArticleService() = getService<ArticleApiService>(defaultClient)

    fun getUserService() = getService<UserApiService>(secureClient)

    fun getEventService() = getService<EventApiService>(secureClient)

    fun getMarketplaceService() = getService<MarketplaceApiService>(secureClient)

    fun getOffreService() = getService<JobOfferApiService>(secureClient)

    fun getQuizService() = getService<QuizApiService>(secureClient)

    fun getAdminService() = getService<AdminApiService>(secureClient)

    fun getCategoryService() = getService<CategoryApiService>(secureClient)

    fun getProductService() = getService<ProductApiService>(secureClient)

    /**
     * Initialize OkhttpClient with token authenticator
     */
    private fun secureHttpInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(context))
        .authenticator(TokenAuthenticator(context))
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .callTimeout(2, TimeUnit.MINUTES)
        .build()

    /**
     * Initialize OkhttpClient with a default interceptor
     */
    private fun defaultInterceptor(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .callTimeout(2, TimeUnit.MINUTES)
            .build()
}