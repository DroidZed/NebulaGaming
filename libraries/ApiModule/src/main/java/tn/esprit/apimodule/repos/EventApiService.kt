package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tn.esprit.apimodule.models.Event

interface EventApiService {

    @GET("events/today")
    suspend fun getOfToday(): Response<List<Event>>

    @GET("events")
    suspend fun getOfMonth(@Query("month") month: Int): Response<List<Event>>
}