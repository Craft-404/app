package com.craft404.sainyojit.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
