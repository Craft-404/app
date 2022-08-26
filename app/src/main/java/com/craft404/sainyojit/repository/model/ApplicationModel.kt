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
    val userId: String?,

    @SerializedName("status")
    val status: Int,
) : Parcelable

@Parcelize
data class ApplicationUserModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("scheme")
    val scheme: SchemeModel,

    @SerializedName("userId")
    val userId: UserModel?,

    @SerializedName("status")
    val status: Int,
) : Parcelable
