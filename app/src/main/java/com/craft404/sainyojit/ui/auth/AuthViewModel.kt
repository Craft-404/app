package com.craft404.sainyojit.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.model.EmployeeModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.PrefManager.API_TOKEN
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.getResult
import com.craft404.videocall.util.VideoCallObjectController
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val _result: MutableLiveData<Result<EmployeeModel>> = MutableLiveData()
    val result: LiveData<Result<EmployeeModel>>
        get() = _result

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _result.postValue(Result.Loading())
            val result = getResult {
                AppObjectController.commonNetworkService.login(mapOf("email" to email, "password" to password))
            }
            _result.postValue(result)
            if (result is Result.Success) {
                result.data.update()
                if (result.data.token != null) {
                    PrefManager.put(API_TOKEN, result.data.token)
                    VideoCallObjectController.init(application = getApplication(), apiToken = result.data.token)
                }
            }
        }
    }

}