package com.craft404.sainyojit.util

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.HttpException
import com.craft404.sainyojit.R
import com.google.android.material.textfield.TextInputEditText
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun ImageView.loadImage(url: String, @DrawableRes placeholder: Int) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadImage(@DrawableRes drawable: Int) {
    Glide.with(this)
        .load(drawable)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun TextInputEditText.isEmpty(): Boolean {
    return this.text.toString().isEmpty()
}

fun TextInputEditText.isNotEmpty(): Boolean {
    return this.text.toString().isNotEmpty()
}

fun showToast(msg: String?) {
    Toast.makeText(
        AppObjectController.sainyojitApplication,
        msg ?: AppObjectController.sainyojitApplication.getString(R.string.something_went_wrong),
        Toast.LENGTH_SHORT
    ).show()
}

fun Exception.showAppropriateMsg(application: Application = AppObjectController.sainyojitApplication) {
    when (this) {
        is HttpException -> {
            showToast(application.getString(R.string.something_went_wrong))
        }
        is SocketTimeoutException, is UnknownHostException -> {
            showToast(application.getString(R.string.internet_not_available))
        }
        else -> {
            showToast(application.getString(R.string.something_went_wrong))
        }
    }
}

fun Throwable.showAppropriateMsg(application: Application = AppObjectController.sainyojitApplication) {
    when (this) {
        is HttpException -> {
            showToast(application.getString(R.string.something_went_wrong))
        }
        is SocketTimeoutException, is UnknownHostException -> {
            showToast(application.getString(R.string.internet_not_available))
        }
        else -> {
            showToast(application.getString(R.string.something_went_wrong))
        }
    }
}

fun Context.showDateTimePicker(
    onDateTimeSelected: (calendar: Calendar) -> Unit
) {
    val calendar = java.util.Calendar.getInstance()
    DatePickerDialog(
        this,
        { _, year, month, date ->
            calendar.set(year, month, date)
            TimePickerDialog(
                this,
                { _, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    onDateTimeSelected(calendar)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            ).show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun TextView.setDate(calendar: Calendar) {
    this.text = String.format(
        "%s %s %s",
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
        calendar.get(Calendar.YEAR)
    )
}

fun TextView.setTime(calendar: Calendar) {
    this.text = String.format(
        "%s:%s %s",
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        if (calendar.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM"
    )
}

fun TextView.setDateTime(calendar: Calendar) {
    this.text = String.format(
        "%s:%s%s %s %s, %s",
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        if (calendar.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM",
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
        calendar.get(Calendar.YEAR)
    )
}

fun Date.getDateString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun Date.getTimeString(): String {
    val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
    return sdf.format(this)
}