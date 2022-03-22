package com.example.a020dagger2.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.a020dagger2.data.network.Resource
import com.example.a020dagger2.data.repository.AuthRepository
import com.example.a020dagger2.data.responses.LoginResponse
import com.example.a020dagger2.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        repository.saveAccessTokens(accessToken, refreshToken)
    }
}