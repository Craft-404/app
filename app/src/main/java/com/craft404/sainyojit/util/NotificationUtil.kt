package com.craft404.sainyojit.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationUtil(private val context: Context) {
    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun init() {
        createChannels("Sainyojit_Tasks", "Tasks", "", NotificationManager.IMPORTANCE_DEFAULT)
        createChannels("Sainyojit_Mentions", "Mentions", "", NotificationManager.IMPORTANCE_HIGH)
        createChannels("Sainyojit_Deadlines", "Deadlines", "", NotificationManager.IMPORTANCE_MAX)
        createChannels("Sainyojit_Calls", "Calls", "", NotificationManager.IMPORTANCE_MAX)
        createChannels("Sainyojit_Others", "Others", "", NotificationManager.IMPORTANCE_LOW)
    }

    private fun createChannels(channelId: String, channelName: String, channelDescription: String, importance: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                importance
            ).apply {
                description = channelDescription
                enableLights(true)
                enableVibration(true)
                setShowBadge(true)
                setBypassDnd(true)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}