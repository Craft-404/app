package com.craft404.sainyojit.util.interceptor

import android.content.Intent
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class StatusCodeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code in 401..403) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    PrefManager.clear()
                    AppObjectController.commonNetworkService.logout()
                    AppObjectController.sainyojitApplication.startActivity(
                        Intent(
                            AppObjectController.sainyojitApplication,
                            com.craft404.sainyojit.ui.AuthActivity::class.java
                        ).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        }
                    )
                } catch (e: Exception) {
                    showToast(e.message)
                }
            }
        }
        return response
    }
}