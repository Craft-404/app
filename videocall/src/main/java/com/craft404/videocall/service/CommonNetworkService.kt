package com.craft404.videocall.service

import retrofit2.http.GET

internal interface CommonNetworkService {
    @GET("/rtc/{channel}/{role}/{tokentype}/{uid}")
    suspend fun generateRTCToken()
}