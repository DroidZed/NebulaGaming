package tn.esprit.apimodule.repos

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.Category
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.Product

interface MarketplaceApiService {

    companion object {

        private const val SUB_P_PATH = "product"
    }

    @GET("category")
    suspend fun getAll(): Response<List<Category>>

    @POST(SUB_P_PATH)
    @Multipart
    suspend fun publishProduct(
        @Query("categoryId") categoryId: String,
        /* @Part name: String,
         @Part price: Float,
         @Part description: String,
         @Part starImage: MultipartBody.Part,
         @Part quantity: Int*/
        @PartMap body: HashMap<String, RequestBody>
    ): Response<GenericResp>

    @GET(SUB_P_PATH)
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("$SUB_P_PATH/{id}")
    suspend fun getOneProduct(@Path("id") idProd: String): Response<List<Product>>

    @DELETE("$SUB_P_PATH/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<GenericResp>

    @GET("$SUB_P_PATH/myProducts")
    suspend fun getMyProducts(@Query("userId") userId: String): Response<List<Product>>

    @PUT("$SUB_P_PATH/hide/{id}")
    suspend fun hideProduct(@Path("id") id: String): Response<List<Product>>

    @PUT("$SUB_P_PATH/update2/{id}")
    suspend fun updateWithoutImage(
        @Path("id") id: String,
        @Query("categoryName") categoryName: String,
        @Body updateBody: HashMap<String, Any>
    ): Response<List<Product>>

    @Multipart
    @PUT("$SUB_P_PATH/update/{id}")
    suspend fun updateWithImage(
        @Path("id") id: String,
        @Query("categoryName") categoryName: String,
        /* @Part name: String,
         @Part price: Float,
         @Part description: String,
         @Part starImage: MultipartBody.Part,
         @Part quantity: Int*/
        @PartMap body: HashMap<String, RequestBody>
    ): Response<List<Product>>
}