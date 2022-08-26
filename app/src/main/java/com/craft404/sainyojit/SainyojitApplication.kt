package com.craft404.sainyojit

import android.app.Application
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.NotificationUtil

class SainyojitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppObjectController.init(this)
        NotificationUtil(this).init()
    }
}