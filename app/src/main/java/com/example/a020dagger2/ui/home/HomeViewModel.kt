package com.example.a020dagger2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.a020dagger2.data.network.Resource
import com.example.a020dagger2.data.repository.UserRepository
import com.example.a020dagger2.data.responses.LoginResponse
import com.example.a020dagger2.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

}