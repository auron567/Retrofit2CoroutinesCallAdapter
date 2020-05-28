package com.example.retrofit2coroutinescalladapter.repository

import com.example.retrofit2coroutinescalladapter.data.model.Movie
import com.example.retrofit2coroutinescalladapter.data.model.Result
import com.example.retrofit2coroutinescalladapter.data.network.MovieService
import com.example.retrofit2coroutinescalladapter.data.network.adapter.NetworkResponse
import com.example.retrofit2coroutinescalladapter.di.API_KEY
import timber.log.Timber

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    override suspend fun getMoviesSuccess() = getMovies(API_KEY)

    override suspend fun getMoviesFailure() = getMovies("")

    private suspend fun getMovies(apiKey: String): Result<List<Movie>> {
        val response = movieService.getMovies(apiKey)
        Timber.d("$response")

        return when (response) {
            is NetworkResponse.Success -> {
                val movies = response.body.movies
                Result.Success(movies)
            }
            is NetworkResponse.ApiError -> {
                val message = response.body.statusMessage
                Result.Error(message)
            }
            is NetworkResponse.NetworkError -> {
                val message = response.error.localizedMessage
                Result.Error(message)
            }
            is NetworkResponse.UnknownError -> {
                val message = response.error?.localizedMessage
                Result.Error(message)
            }
        }
    }
}
