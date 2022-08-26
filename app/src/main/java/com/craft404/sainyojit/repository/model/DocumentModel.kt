package com.craft404.sainyojit.repository.model

import com.google.gson.annotations.SerializedName

data class DocumentModel(
    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("uploadedBy")
    val uploadedBy: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("documentLink")
    val documentLink: String,

    @SerializedName("organisationName")
    val organisationName: String,

    @SerializedName("organisationContact")
    val organisationContact: String,

    @SerializedName("metadata")
    val metadata: String?,

    @SerializedName("isVerified")
    val isVerified: Boolean,
)
