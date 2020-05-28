package com.example.retrofit2coroutinescalladapter.data.network

import com.example.retrofit2coroutinescalladapter.data.model.MovieError
import com.example.retrofit2coroutinescalladapter.data.model.MovieResponse
import com.example.retrofit2coroutinescalladapter.data.network.adapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): NetworkResponse<MovieResponse, MovieError>
}