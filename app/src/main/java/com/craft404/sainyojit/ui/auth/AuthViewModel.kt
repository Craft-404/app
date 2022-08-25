package com.craft404.sainyojit.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.model.Employee
import com.craft404.sainyojit.util.*
import com.craft404.sainyojit.util.PrefManager.API_TOKEN
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {


    private val _result: MutableLiveData<Result<Employee>> = MutableLiveData()

    val result: LiveData<Result<Employee>>
        get() = _result

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _result.postValue(Result.Loading())
                val response = getResult {
                    AppObjectController.commonNetworkService.login(mapOf("email" to email, "password" to password))
                }
                _result.postValue(response)
                if (response is Result.Success) {
                    (response.data).update()
                    PrefManager.put(API_TOKEN, response.data.token)
                }
            } catch (e: Exception) {
                _result.postValue(Result.Error(e))
                e.showAppropriateMsg()
            }
        }
    }

}