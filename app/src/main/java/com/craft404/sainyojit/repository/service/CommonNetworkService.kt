package com.craft404.sainyojit.repository.service

import com.craft404.sainyojit.repository.model.Employee
import com.craft404.sainyojit.util.DIR
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author yashkasera
 * Created 21/08/22 at 11:49 PM
 */
interface CommonNetworkService {
    @POST("$DIR/login/")
    suspend fun login(@Body map: Map<String, String>): Response<Employee>

    @GET("$DIR/logout/")
    suspend fun logout(): Response<Any>

    @POST("$DIR/verify/")
    suspend fun verifyFCM(mapOf: Map<String, String>): Response<Any>
}