package com.craft404.sainyojit.util

import com.craft404.sainyojit.BuildConfig
import com.craft404.sainyojit.SainyojitApplication
import com.craft404.sainyojit.repository.service.CommonNetworkService
import com.craft404.sainyojit.util.interceptor.AuthorizationInterceptor
import com.craft404.sainyojit.util.interceptor.StatusCodeInterceptor
import com.google.gson.Gson
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppObjectController {
    companion object {

        @JvmStatic
        lateinit var gson: Gson

        @JvmStatic
        lateinit var sainyojitApplication: SainyojitApplication

        @JvmStatic
        lateinit var retrofit: Retrofit

        @JvmStatic
        lateinit var commonNetworkService: CommonNetworkService


        fun init(sainyojitApplication: SainyojitApplication) {
            this.sainyojitApplication = sainyojitApplication
            gson = Gson()
            val client = OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(StatusCodeInterceptor())
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