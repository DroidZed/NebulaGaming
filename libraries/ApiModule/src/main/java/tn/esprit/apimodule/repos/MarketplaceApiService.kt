package tn.esprit.apimodule.repos

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.Category
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.PagedProductsData
import tn.esprit.apimodule.models.Product

interface MarketplaceApiService {

    companion object {
        private const val API_ID = "product"
    }

    @GET("category")
    suspend fun getAll(): Response<List<Category>>

    @POST(API_ID)
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

    @GET(API_ID)
    suspend fun getAllProducts(@Query("page") page:Int): Response<PagedProductsData>

    @GET("$API_ID/{id}")
    suspend fun getOneProduct(@Path("id") idProd: String): Response<Product>

    @DELETE("$API_ID/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<GenericResp>

    @GET("$API_ID/myProducts")
    suspend fun getMyProducts(@Query("userId") userId: String): Response<List<Product>>

    @PUT("$API_ID/hide/{id}")
    suspend fun hideProduct(@Path("id") id: String): Response<List<Product>>

    @PUT("$API_ID/update2/{id}")
    suspend fun updateWithoutImage(
        @Path("id") id: String,
        @Query("categoryName") categoryName: String,
        @Body updateBody: HashMap<String, Any>
    ): Response<List<Product>>

    @Multipart
    @PUT("$API_ID/update/{id}")
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