package com.craft404.sainyojit.repository.service

import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.*
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

    @GET("$DIR/ticket/task")
    suspend fun fetchTasks(
        @Query("startDate") start: Date,
        @Query("endDate") end: Date,
        @Query("overdue") overdue: Boolean
    ): Response<TasksModel>

    @GET("$DIR/ticket/approval")
    suspend fun fetchApproval(
        @Query("startDate") start: Date,
        @Query("endDate") end: Date,
        @Query("overdue") overdue: Boolean
    ): Response<ApprovalModel>

    @PATCH("$DIR/ticket/{id}/")
    suspend fun updateTicket(@Body map: Map<String, String>, @Path("id") id: String): Response<Any>

    @GET("$DIR/document/{id}/")
    suspend fun fetchDocuments(@Path("id") applicationId: String): Response<List<DocumentModel>>

    @GET("$DIR/application/tickets/{id}/")
    suspend fun fetchTickets(@Path("id") applicationId: String): Response<List<TicketEntity>>

    @GET("$DIR/application/{id}/")
    suspend fun fetchApplicationDetails(@Path("id") applicationId: String): Response<ApplicationUserModel>

    @PATCH("$DIR/ticket/approval/{id}/")
    suspend fun approveTicket(@Path("id") ticketId: String): Response<Any>
}