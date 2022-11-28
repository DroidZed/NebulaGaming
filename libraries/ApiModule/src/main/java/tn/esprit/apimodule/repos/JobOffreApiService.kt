package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.*
import tn.esprit.apimodule.models.*

interface JobOffreApiService {
    @GET("offrejob")
    suspend fun getAllOfre(): Response<OffreJob>

    @POST("offrejob/saveoffrejob")
    suspend fun AddOffreJob(@Body offrejobbody: OffreJob): Response<GenericResp>

    @Headers("Content-Type:application/json")
    @PUT("offrejob/updateoffre/{id}")
    suspend fun updateOffreJob(
        @Path("id") id: String,
        @Body offrejobbody: OffreJob
    ): Response<GenericResp>

    @DELETE("offrejob/deleteoffre/{id}")
    suspend fun deleteOffreJob(@Path("id") id: String): Response<GenericResp>

    @GET("offrejob/{id}")
    suspend fun getOffreJobbyId(@Path("id") id: String): Response<OffreJob>


}