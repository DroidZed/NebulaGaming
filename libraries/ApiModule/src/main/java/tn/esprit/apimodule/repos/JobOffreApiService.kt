package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import tn.esprit.apimodule.models.*

interface JobOffreApiService {
    @GET("offrejob")
    suspend fun getAllOfre(): Response<OffreJob>

    @Headers("Content-Type:application/json")
    @POST("offrejob/saveoffrejob")
    suspend fun AddOffreJob(@Body offrejobbody: OffreJob): Response<GenericResp>

}