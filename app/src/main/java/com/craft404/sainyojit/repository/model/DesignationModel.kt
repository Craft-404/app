package com.craft404.sainyojit.repository.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DesignationModel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
) : Parcelable