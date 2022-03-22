package com.example.a020dagger2.ui.auth

interface Factory<T> {
    fun create() : T
}