package com.craft404.sainyojit.repository.entity

import android.app.Application
import android.os.Parcelable
import androidx.room.PrimaryKey
import com.craft404.sainyojit.repository.model.ApplicationModel
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

//@Entity(tableName = "ticket")
@Parcelize
data class TicketEntity(
    @SerializedName("title")
    val title: String,

    @SerializedName("applicationId")
    val applicationId: ApplicationModel?,

    @SerializedName("reporter")
    val reporter: EmployeeModel,

    @SerializedName("description")
    val description: String?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("documentId")
    val documentId: String?,

    @SerializedName("startDate")
    val startDate: Date?,

    @SerializedName("dueDate")
    val dueDate: Date?,

    @SerializedName("category")
    val category: String,

    @SerializedName("priority")
    val priority: Int,

    @SerializedName("assignees")
    val assignees: List<EmployeeModel>?,

    @SerializedName("createdAt")
    val createdAt: Date,

    @SerializedName("updatedAt")
    val updatedAt: Date,
) : Parcelable {
    @SerializedName("_id")
    @PrimaryKey
    var id: String = ""
}

enum class TicketCategory {
    @SerializedName("Approval")
    APPROVAL,

    @SerializedName("Task")
    TASK
}
