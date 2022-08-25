package com.craft404.sainyojit

import android.app.Application
import com.craft404.sainyojit.util.AppObjectController

class SainyojitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppObjectController.init(this)
    }
}