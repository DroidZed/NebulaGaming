package tn.esprit.apimodule.repos


import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.GenericResp
import tn.esprit.apimodule.models.whishlist

interface WishlistApiService {

    @POST("wishlist/add")
    suspend fun addProductToWishlist(@Body whishlist: whishlist): Response<GenericResp>
}