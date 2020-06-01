package com.example.retrofit2coroutinescalladapter.utils

import com.example.retrofit2coroutinescalladapter.data.model.Movie
import com.example.retrofit2coroutinescalladapter.data.model.MovieError
import com.example.retrofit2coroutinescalladapter.data.model.MovieResponse
import java.util.*

object MovieDataFactory {

    fun makeMovieResponse() = MovieResponse(makeMovieList())

    fun makeMovieList(): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(20) {
            movies.add(makeMovie())
        }

        return movies
    }

    fun makeMovieError() = MovieError(
        statusCode = "${UUID.randomUUID()}",
        statusMessage = "${UUID.randomUUID()}"
    )

    private fun makeMovie() = Movie(
        id = "${UUID.randomUUID()}",
        title = "${UUID.randomUUID()}"
    )
}