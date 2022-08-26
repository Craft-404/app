package com.craft404.sainyojit.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BureauModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("managerId")
    val managerId: String,
) : Parcelable