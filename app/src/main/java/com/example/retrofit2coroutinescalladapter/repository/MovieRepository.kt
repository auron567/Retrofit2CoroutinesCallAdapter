package com.example.retrofit2coroutinescalladapter.repository

import com.example.retrofit2coroutinescalladapter.data.model.Movie
import com.example.retrofit2coroutinescalladapter.data.model.Result

interface MovieRepository {

    suspend fun getMoviesSuccess(): Result<List<Movie>>

    suspend fun getMoviesFailure(): Result<List<Movie>>
}