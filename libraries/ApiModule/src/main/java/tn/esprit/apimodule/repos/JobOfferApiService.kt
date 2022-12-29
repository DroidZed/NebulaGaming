package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*

interface JobOfferApiService {

    companion object {
        private const val API_ID = "offrejob"
    }

    @GET(API_ID)
    suspend fun getAllOffers(): Response<MutableList<OffreJob>>

    @POST("$API_ID/saveoffrejob")
    suspend fun addOfferJob(
        @Query("companyId") companyId: String,
        @Body jobOfferBody: OffreJob
    ): Response<GenericResp>

    @Headers("Content-Type:application/json")
    @PUT("$API_ID/updateoffre/{id}")
    suspend fun updateOffreJob(
        @Path("id") id: String,
        @Body offrejobbody: OffreJob
    ): Response<GenericResp>

    @DELETE("$API_ID/deleteoffre/{id}")
    suspend fun deleteOffreJob(@Path("id") id: String): Response<GenericResp>

    @GET("$API_ID/{id}")
    suspend fun getOffreJobbyId(@Path("id") id: String): Response<OffreJob>


}