package com.craft404.sainyojit.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationData(
    val title: String,
    val subTitle: String,
    val action: NotificationAction = NotificationAction.OPEN_APP,
    val data: String? = null
) : Parcelable

enum class NotificationAction {
    OPEN_TASK,
    OPEN_MENTION,
    OPEN_CALL,
    OPEN_APP
}