package com.craft404.sainyojit.ui

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import com.craft404.sainyojit.R
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setPriority")
fun setPriority(imageView: AppCompatImageView, priority: Int) {
    imageView.setImageResource(
        when (priority) {
            1 -> R.drawable.ic_priority_lowest
            2 -> R.drawable.ic_priority_low
            3 -> R.drawable.ic_priority_medium
            4 -> R.drawable.ic_priority_high
            5 -> R.drawable.ic_priority_highest
            else -> R.drawable.ic_priority_medium
        }
    )
}

@BindingAdapter("setPriority")
fun setPriority(chip: Chip, priority: Int) {
    chip.chipIcon = ContextCompat.getDrawable(
        chip.context,
        when (priority) {
            1 -> R.drawable.ic_priority_lowest
            2 -> R.drawable.ic_priority_low
            3 -> R.drawable.ic_priority_medium
            4 -> R.drawable.ic_priority_high
            5 -> R.drawable.ic_priority_highest
            else -> R.drawable.ic_priority_medium
        }
    )
    chip.text = when (priority) {
        1 -> "Lowest"
        2 -> "Low"
        3 -> "Medium"
        4 -> "High"
        5 -> "Highest"
        else -> "Medium"
    }
}

@BindingAdapter("onSearch")
fun setOnSearch(view: TextInputEditText, query: MutableStateFlow<String>) {
    view.doAfterTextChanged { query.value = it.toString() }
}

@BindingAdapter("setAssignees")
fun setAssignees(view: MaterialTextView, assignees: List<EmployeeModel>?) {
    if (assignees.isNullOrEmpty())
        view.visibility = View.GONE
    else {
        view.visibility = View.VISIBLE
        view.text = assignees.joinToString(separator = ", ") { it.name }
    }
}

@BindingAdapter("setTime")
fun setTime(view: MaterialTextView, date: Date) {
    val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
    view.text = sdf.format(date)
}

@BindingAdapter("setTime")
fun setTime(view: Chip, date: Date) {
    val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
    view.text = sdf.format(date)
}