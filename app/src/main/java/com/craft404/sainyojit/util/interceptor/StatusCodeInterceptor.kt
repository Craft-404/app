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
        return chain.request().let {
            val response = chain.proceed(it)
            if (response.code in 401..403) {
                //TODO: Handle unauthorized
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        AppObjectController.commonNetworkService.logout()
                        PrefManager.clear()
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
            chain.proceed(it)
        }
    }
}