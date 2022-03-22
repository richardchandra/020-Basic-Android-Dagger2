package com.example.a020dagger2.ui.auth

import com.example.a020dagger2.data.repository.AuthRepository

class AuthViewModelFactory(
    private val authRepository: AuthRepository
) : Factory<AuthViewModel> {
    override fun create(): AuthViewModel {
        return AuthViewModel(authRepository)
    }
}