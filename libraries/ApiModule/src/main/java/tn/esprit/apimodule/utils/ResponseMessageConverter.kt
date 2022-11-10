package tn.esprit.apimodule.utils

import com.google.gson.Gson
import org.json.JSONObject

data class ResponseConverter<out T>(val json: JSONObject, val data: T?) {

    companion object {

        inline fun <reified T> convert(json: JSONObject): ResponseConverter<T> =
            ResponseConverter(json, Gson().fromJson(json.toString(), T::class.java))
    }
}