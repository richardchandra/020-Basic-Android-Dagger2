package com.example.a020dagger2.data.repository

import com.example.a020dagger2.data.UserPreferences
import com.example.a020dagger2.data.network.AuthApi
import javax.inject.Inject


class AuthRepository @Inject constructor (
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }

}