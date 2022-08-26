package com.craft404.sainyojit.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    fun updateFCMToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            viewModelScope.launch {
                try {
                    val response = AppObjectController.commonNetworkService.updateFCM(mapOf("fcmToken" to it))
                    if (response.isSuccessful) {
                        val employeeModel = EmployeeModel.getInstance()
                        employeeModel.fcmToken = it
                        employeeModel.update()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            PrefManager.clear()
            AppObjectController.commonNetworkService.logout()
            AppObjectController.sainyojitApplication.startActivity(
                Intent(
                    AppObjectController.sainyojitApplication,
                    AuthActivity::class.java
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
            )
        }
    }

}