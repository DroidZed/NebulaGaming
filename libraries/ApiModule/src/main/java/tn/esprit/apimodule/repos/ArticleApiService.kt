package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tn.esprit.apimodule.models.PagedArticlesData


interface ArticleApiService {

    @GET("articles")
    suspend fun downloadArticles(@Query("page") pageNumber: Int? = 1): Response<PagedArticlesData>
}