package tn.esprit.roommodule.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class ArrayListToJsonConverter<T> {

    private inline fun <reified T> Gson.fromJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun toJson(value: List<T>): String? = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String): List<T>? =
        try {
            Gson().fromJson<List<T>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
}