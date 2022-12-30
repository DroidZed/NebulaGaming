package tn.esprit.apimodule.repos

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import tn.esprit.apimodule.models.*

interface AdminApiService {

    @GET("admin/getAllMethod")
    suspend fun getCountAllUsers(): Response<AllCountUsers>

    @GET("admin/getCountAllEnableDiscableCount")
    suspend fun getCountAllEnableDiscableCount(): Response<AllEDusers>

    @GET("admin/statProduct")
    suspend fun getAllMethodProducts(): Response<AllCategProduct>

    @GET("admin/allUsers")
    suspend fun getAllUsers(): Response<MutableList<User>>

    @PATCH("admin/enable/{id}")
    suspend fun enableUser(id: String): Response<User>

    @PATCH("admin/disable/{id}")
    suspend fun disableUser(id: String): Response<User>
}