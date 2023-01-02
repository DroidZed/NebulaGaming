package tn.esprit.apimodule.repos

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.Product

interface ProductApiService {

    @POST("product")
    @Multipart
    suspend fun uploadProduct(
        @Query("categoryId") categoryId: String,
        @Part image: MultipartBody.Part,
        @PartMap product: HashMap<String,Any>): Response<GenericResp>

    @GET("product/myProducts")
    suspend fun getMyProducts(
        @Query("userId") userId: String
    ): Response<MutableList<Product>>


}