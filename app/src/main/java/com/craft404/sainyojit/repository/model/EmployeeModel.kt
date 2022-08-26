package com.craft404.sainyojit.repository.model

import android.os.Parcelable
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.PrefManager.EMPLOYEE_DATA
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class EmployeeModel(
    @SerializedName("_id")
    var id: String,

    @SerializedName("username")
    val username: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("token")
    val token: String?,

    @SerializedName("dateOfJoining")
    val dateOfJoining: Date?,

    @SerializedName("fcmToken")
    var fcmToken: String?,

    @SerializedName("bureauId")
    var bureauId: BureauModel?,

    @SerializedName("designationId")
    var designationId: DesignationModel?,
) : Parcelable {

    companion object {
        fun getInstance(): EmployeeModel {
            return AppObjectController.gson.fromJson(PrefManager.getString(EMPLOYEE_DATA), EmployeeModel::class.java)
        }

    }

    fun update() {
        PrefManager.put(EMPLOYEE_DATA, AppObjectController.gson.toJson(this, EmployeeModel::class.java))
    }
}