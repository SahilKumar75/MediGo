package com.example.musical.loginscreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

//    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
//        emit(Result.loading())
//        try {
//            val data = loginRepository.login(username, password)
//            emit(Result.success(data))
//        } catch (exception: Exception) {
//            emit(Result.error(exception.message ?: "Error occurred"))
//        }
//    }
}
