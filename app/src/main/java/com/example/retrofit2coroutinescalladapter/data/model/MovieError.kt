package com.example.retrofit2coroutinescalladapter.data.model

import com.google.gson.annotations.SerializedName

data class MovieError(
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("status_message") val statusMessage: String
)