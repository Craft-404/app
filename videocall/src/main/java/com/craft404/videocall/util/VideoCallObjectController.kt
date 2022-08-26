package com.craft404.videocall.util

import android.app.Application
import com.craft404.videocall.BuildConfig
import com.craft404.videocall.service.CommonNetworkService
import com.craft404.videocall.util.PrefManager.API_TOKEN
import com.google.gson.Gson
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideoCallObjectController {
    companion object {
        @JvmStatic
        internal lateinit var gson: Gson

        @JvmStatic
        internal lateinit var application: Application

        @JvmStatic
        internal lateinit var retrofit: Retrofit

        @JvmStatic
        internal lateinit var commonNetworkService: CommonNetworkService

        fun updateToken(apiToken: String) {
            PrefManager.put(API_TOKEN, apiToken)
        }

        fun init(application: Application, apiToken: String) {
            this.application = application
            updateToken(apiToken)
            gson = Gson()
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder()
                    request.addHeader("Authorization", PrefManager.getString(API_TOKEN))
                    chain.proceed(request.build())
                })
            if (BuildConfig.DEBUG) {
                client.addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                client.addInterceptor(OkHttpProfilerInterceptor())
            }
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            commonNetworkService = retrofit.create(CommonNetworkService::class.java)
        }
    }
}

