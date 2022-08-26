package com.craft404.sainyojit.repository.model


import com.google.gson.annotations.SerializedName

data class AnnouncementModel(
    @SerializedName("bureauId")
    val bureauId: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("updatedAt")
    val updatedAt: String,
)