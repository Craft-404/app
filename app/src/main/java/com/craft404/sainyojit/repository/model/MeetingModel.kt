package com.craft404.sainyojit.repository.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MeetingModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("channelID")
    val channelID: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("agenda")
    val agenda: String,

    @SerializedName("startTime")
    val startTime: Date,

    @SerializedName("attendees")
    val attendees: List<String>,
)