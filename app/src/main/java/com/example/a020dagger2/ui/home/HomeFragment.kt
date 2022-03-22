package com.example.a020dagger2.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a020dagger2.R
import com.example.a020dagger2.data.network.RemoteDataSource
import com.example.a020dagger2.data.network.Resource
import com.example.a020dagger2.data.network.UserApi
import com.example.a020dagger2.data.repository.UserRepository
import com.example.a020dagger2.data.responses.User
import com.example.a020dagger2.databinding.FragmentHomeBinding
import com.example.a020dagger2.ui.handleApiError
import com.example.a020dagger2.ui.logout
import com.example.a020dagger2.ui.visible

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(UserApi::class.java, requireContext())
        val userRepository = UserRepository(api)
        viewModel = HomeViewModel(userRepository)

        binding.progressbar.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                }
            }
        }

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }
}