package com.craft404.sainyojit.repository.service

import android.util.Log
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.PrefManager.FCM_TOKEN
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        try {
            Log.d("FirebaseNotificationService.kt", "YASH => onNewToken:24 $token")
            CoroutineScope(Dispatchers.IO).launch {
                val response = AppObjectController.commonNetworkService.updateFCM(mapOf("fcmToken" to token))
                if (response.isSuccessful) {
                    PrefManager.put(FCM_TOKEN, token)
                    val employeeModel = EmployeeModel.getInstance()
                    employeeModel.fcmToken = token
                    employeeModel.update()
                } else {
                    Log.d("FirebaseNotificationService.kt", "YASH => onNewToken:26 ${response.message()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

    }
}