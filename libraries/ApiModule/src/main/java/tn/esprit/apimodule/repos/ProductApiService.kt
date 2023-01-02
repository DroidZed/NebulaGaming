package tn.esprit.apimodule.repos

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.Product

interface ProductApiService {

    @POST("product/")
    @Headers("Content-Type:application/json")
    @Multipart
    suspend fun uploadProduct(
        @Query("CategoryId") CategoryId: String,
        @Query("publisher") publisher: String,
        @Part image: MultipartBody.Part,
        @PartMap product: HashMap<String,Any>): Response<GenericResp>
}