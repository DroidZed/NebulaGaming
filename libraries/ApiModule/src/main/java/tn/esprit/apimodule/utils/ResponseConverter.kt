package tn.esprit.apimodule.utils

import com.google.gson.Gson

data class ResponseConverter<out T>(val json: String, val data: T?) {

    companion object {

        inline fun <reified T> convert(json: String): ResponseConverter<T> =
            ResponseConverter(json, Gson().fromJson(json, T::class.java))
    }
}