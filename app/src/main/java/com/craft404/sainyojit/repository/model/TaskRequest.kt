package com.craft404.sainyojit.repository.model

import com.craft404.sainyojit.repository.entity.TicketCategory
import com.google.gson.annotations.SerializedName

data class TaskRequest(
    @SerializedName("title")
    val title: String,

    @SerializedName("assignees")
    val assignees: List<String>,

    @SerializedName("description")
    val description: String,

    @SerializedName("startDate")
    val startDate: Long,

    @SerializedName("dueDate")
    val dueDate: Long?,

    @SerializedName("priority")
    val priority: Int,

    @SerializedName("category")
    val category: TicketCategory = TicketCategory.TASK
)
