package com.craft404.sainyojit.repository.service

import com.craft404.sainyojit.repository.model.DashboardModel
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.repository.model.TaskRequest
import com.craft404.sainyojit.repository.model.TasksModel
import com.craft404.sainyojit.util.DIR
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface CommonNetworkService {
    @POST("$DIR/auth/login/")
    suspend fun login(@Body map: Map<String, String>): Response<EmployeeModel>

    @GET("$DIR/auth/logout/")
    suspend fun logout(): Response<Any>

    @PATCH("$DIR/auth/employee/fcm/")
    suspend fun updateFCM(@Body mapOf: Map<String, String>): Response<Any>

    @GET("$DIR/dashboard/")
    suspend fun fetchDashboard(): Response<DashboardModel>

    @POST("$DIR/ticket/")
    suspend fun createTask(@Body taskRequest: TaskRequest): Response<Any>

    @GET("$DIR/employee/search")
    suspend fun getEmployeeSearchResults(@Query("q") query: String): Response<List<EmployeeModel>>

    @GET("$DIR/ticket/date")
    suspend fun fetchTasks(
        @Query("startDate") start: Date,
        @Query("endDate") end: Date,
        @Query("overdue") overdue: Boolean
    ): Response<TasksModel>

    @PATCH("$DIR/ticket/{id}/")
    suspend fun updateTicket(@Body map: Map<String, String>, @Path("id") id: String): Response<Any>
}