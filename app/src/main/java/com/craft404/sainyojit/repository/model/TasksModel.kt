package com.craft404.sainyojit.repository.model

import com.craft404.sainyojit.repository.entity.TicketEntity
import com.google.gson.annotations.SerializedName

data class TasksModel(
    @SerializedName("tasks")
    val tasks: List<TicketEntity>
)

data class ApprovalModel(
    @SerializedName("approvals")
    val approvals: List<TicketEntity>
)
