package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.GET
import tn.esprit.apimodule.models.Category

interface CategoryApiService {

    @GET("category")
    suspend fun getAll(): Response<List<Category>>
}