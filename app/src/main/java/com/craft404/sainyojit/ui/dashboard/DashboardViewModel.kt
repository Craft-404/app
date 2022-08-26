package com.craft404.sainyojit.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.model.DashboardModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.getResult
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val _result: MutableLiveData<Result<DashboardModel>> = MutableLiveData()
    val result: LiveData<Result<DashboardModel>>
        get() = _result

    init {
        fetchDashboard()
    }

    fun fetchDashboard() {
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val result = getResult { AppObjectController.commonNetworkService.fetchDashboard() }
            _result.postValue(result)
        }
    }

}