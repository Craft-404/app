package com.craft404.sainyojit.util

import android.app.Application
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.HttpException
import com.craft404.sainyojit.R
import com.google.android.material.textfield.TextInputEditText
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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