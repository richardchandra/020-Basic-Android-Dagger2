package com.example.a020dagger2.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a020dagger2.R
import com.example.a020dagger2.data.UserPreferences
import com.example.a020dagger2.data.network.RemoteDataSource
import com.example.a020dagger2.data.network.UserApi
import com.example.a020dagger2.data.repository.UserRepository
import com.example.a020dagger2.ui.auth.AuthActivity
import com.example.a020dagger2.ui.startNewActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    lateinit var userPreferences: UserPreferences
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userPreferences = UserPreferences(this)
        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(UserApi::class.java,this)
        val authRepository = UserRepository(api)
        viewModel = HomeViewModel(authRepository)
    }

    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        userPreferences.clear()
        startNewActivity(AuthActivity::class.java)
    }
}