package tn.esprit.apimodule.repos

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.Product

interface ProductApiService {

    @POST("product")
    @Multipart
    suspend fun publishProduct(
        @Query("categoryId") categoryId: String,
        @Part name: String,
        @Part price: Float,
        @Part description: String,
        @Part starImage: MultipartBody.Part,
        @Part quantity: Int
    ): Response<GenericResp>

    @GET("product")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("product/{id}")
    suspend fun getOneProduct(@Path("id") idProd: String): Response<List<Product>>
}