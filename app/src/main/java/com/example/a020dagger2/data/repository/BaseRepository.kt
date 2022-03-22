package com.example.a020dagger2.data.repository

import com.example.a020dagger2.data.network.BaseApi
import com.example.a020dagger2.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}