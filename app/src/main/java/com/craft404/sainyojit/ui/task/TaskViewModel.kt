package com.craft404.sainyojit.ui.task

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.entity.TicketCategory
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.repository.model.TaskRequest
import com.craft404.sainyojit.repository.model.TasksModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.getResult
import com.craft404.sainyojit.util.showToast
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    var assignee: EmployeeModel? = null
    var startDate: Calendar = Calendar.getInstance()
    var dueDate: Calendar? = null

    private val _result: MutableLiveData<Result<Any>> = MutableLiveData()
    val result: LiveData<Result<Any>>
        get() = _result

    private val _taskResult: MutableLiveData<Result<TasksModel>> = MutableLiveData()
    val taskResult: LiveData<Result<TasksModel>>
        get() = _taskResult

    fun postTicket(
        title: String,
        description: String,
        priority: Int,
        category: TicketCategory,
    ) {
        viewModelScope.launch {
            assignee?.let {
                val result = getResult {
                    AppObjectController.commonNetworkService.createTask(
                        TaskRequest(
                            title = title,
                            description = description,
                            priority = priority,
                            assignees = arrayListOf<String>(it.id),
                            startDate = startDate.timeInMillis,
                            dueDate = dueDate?.timeInMillis,
                            category = category,
                        )
                    )
                }
                _result.postValue(result)
            } ?: showToast("Please select assignee")
        }
    }

    fun fetchTasks(calendar: Calendar, overdue: Boolean) {
        viewModelScope.launch {
            val result = getResult {
                AppObjectController.commonNetworkService.fetchTasks(
                    calendar.apply {
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }.time,
                    calendar.apply {
                        set(Calendar.HOUR_OF_DAY, 23)
                        set(Calendar.MINUTE, 59)
                        set(Calendar.SECOND, 59)
                    }.time,
                    overdue
                )
            }
            _taskResult.postValue(result)
        }
    }
}