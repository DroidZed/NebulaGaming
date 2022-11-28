package tn.esprit.apimodule.utils

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import tn.esprit.authmodule.repos.UserAuthManagerImpl

class AuthInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val authManager = UserAuthManagerImpl(context)

        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        authManager.retrieveUserInfoFromStorage().let {
            requestBuilder.addHeader("Authorization", "Bearer ${it?.token}")
        }

        return chain.proceed(requestBuilder.build())
    }
}
