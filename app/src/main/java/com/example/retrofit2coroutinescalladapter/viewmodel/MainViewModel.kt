package com.example.retrofit2coroutinescalladapter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit2coroutinescalladapter.data.model.Movie
import com.example.retrofit2coroutinescalladapter.data.model.Result
import com.example.retrofit2coroutinescalladapter.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<Result<List<Movie>>>()
    val moviesLiveData: LiveData<Result<List<Movie>>> get() = _moviesLiveData

    fun getMoviesSuccess() {
        viewModelScope.launch {
            val result = repository.getMoviesSuccess()
            _moviesLiveData.value = result
        }
    }

    fun getMoviesFailure() {
        viewModelScope.launch {
            val result = repository.getMoviesFailure()
            _moviesLiveData.value = result
        }
    }
}