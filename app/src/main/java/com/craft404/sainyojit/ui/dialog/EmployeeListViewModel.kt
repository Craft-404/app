package com.craft404.sainyojit.ui.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.getResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class EmployeeListViewModel(application: Application) : AndroidViewModel(application) {
    val query = MutableStateFlow("")

    private var _result = MutableLiveData<Result<List<EmployeeModel>>>()
    val result: LiveData<Result<List<EmployeeModel>>>
        get() = _result


    init {
        setQueryListener()
    }

    private fun search(query: String) {
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val result = getResult { AppObjectController.commonNetworkService.getEmployeeSearchResults(query) }
            _result.postValue(result)
        }
    }

    private fun setQueryListener() {
        viewModelScope.launch {
            query.debounce(500)
                .distinctUntilChanged()
                .flowOn(Dispatchers.Main)
                .collect {
                    if (it.isNotEmpty())
                        search(it)
                }
        }
    }

}
