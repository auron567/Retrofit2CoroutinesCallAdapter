package com.example.retrofit2coroutinescalladapter.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.retrofit2coroutinescalladapter.data.model.Result
import com.example.retrofit2coroutinescalladapter.databinding.ActivityMainBinding
import com.example.retrofit2coroutinescalladapter.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setMoviesLiveDataObserver()
        setButtons()
    }

    private fun setMoviesLiveDataObserver() {
        viewModel.moviesLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                   Timber.d("Success response: ${result.value}")
                }
                is Result.Error -> {
                    Timber.d("Failure response: ${result.message}")
                }
            }
        })
    }

    private fun setButtons() {
        binding.successButton.setOnClickListener { viewModel.getMoviesSuccess() }
        binding.failureButton.setOnClickListener { viewModel.getMoviesFailure() }
    }
}