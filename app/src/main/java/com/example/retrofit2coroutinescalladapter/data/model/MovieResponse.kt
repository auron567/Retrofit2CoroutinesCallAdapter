package com.example.retrofit2coroutinescalladapter.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val movies: List<Movie>
)