package com.craft404.sainyojit.repository.model

import com.craft404.sainyojit.repository.entity.TicketEntity
import com.google.gson.annotations.SerializedName

data class DashboardModel(
    @SerializedName("announcements")
    val announcements: List<AnnouncementModel>,

    @SerializedName("overdue")
    val overdue: List<TicketEntity>,

    @SerializedName("tasks")
    val tasks: List<TicketEntity>,

    @SerializedName("approvals")
    val approvals: List<TicketEntity>,
)
