package tn.esprit.apimodule.repos
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*

interface CategoryApiService {

    @GET("category") suspend fun getAllCategories(): Response<MutableList<Category>>

}