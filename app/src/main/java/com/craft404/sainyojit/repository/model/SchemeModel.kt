package com.craft404.sainyojit.repository.model

import com.google.gson.annotations.SerializedName

data class SchemeModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("maxStatus")
    val maxStatus: Int,

    @SerializedName("minAge")
    val minAge: Int,

    @SerializedName("maxAge")
    val maxAge: Int,
)
