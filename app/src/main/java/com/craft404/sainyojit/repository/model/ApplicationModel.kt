package com.craft404.sainyojit.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApplicationModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("scheme")
    val scheme: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("applicationFormLink")
    val applicationFormLink: String?,
) : Parcelable
