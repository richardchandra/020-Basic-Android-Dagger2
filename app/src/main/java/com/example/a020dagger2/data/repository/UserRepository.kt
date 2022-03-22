package com.example.a020dagger2.data.repository

import com.example.a020dagger2.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }

}