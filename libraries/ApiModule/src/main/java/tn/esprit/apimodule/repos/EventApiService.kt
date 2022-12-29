package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tn.esprit.apimodule.models.Event

interface EventApiService {

    companion object {
        private const val API_ID = "events"
    }

    @GET("$API_ID/today")
    suspend fun getOfToday(): Response<Event>

    @GET(API_ID)
    suspend fun getOfMonthAndYear(@Query("month") month: Int?, @Query("year") year: Int?): Response<List<Event>>
}