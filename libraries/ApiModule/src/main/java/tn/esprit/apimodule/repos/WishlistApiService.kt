package tn.esprit.apimodule.repos


import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp

interface WishlistApiService {


    @POST("wishlist/add")
    suspend fun addProductToWishlist(
        @Query("idUser") idUser: String,
        @Query("idProduct") idProduct: String
    ): Response<GenericResp>


}