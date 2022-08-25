package com.craft404.sainyojit.repository.model

import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.PrefManager.EMPLOYEE_DATA
import com.google.gson.annotations.SerializedName
import java.util.*

data class Employee(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("token")
    val email: String,

    @SerializedName("token")
    val token: String,

    @SerializedName("doj")
    val doj: Date,

    @SerializedName("fcmToken")
    var fcmToken: String?,
) {

    companion object {
        fun getInstance(): Employee {
            return AppObjectController.gson.fromJson(PrefManager.getString(EMPLOYEE_DATA), Employee::class.java)
        }

    }

    fun update() {
        PrefManager.put(EMPLOYEE_DATA, AppObjectController.gson.toJson(this, Employee::class.java))
    }
}