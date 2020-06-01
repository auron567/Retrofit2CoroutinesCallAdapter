package com.example.retrofit2coroutinescalladapter.repository

import com.example.retrofit2coroutinescalladapter.data.model.Result
import com.example.retrofit2coroutinescalladapter.data.network.MovieService
import com.example.retrofit2coroutinescalladapter.data.network.adapter.NetworkResponse
import com.example.retrofit2coroutinescalladapter.utils.MovieDataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals
import java.io.IOException
import java.util.*

class MovieRepositoryImplTest {

    private lateinit var repository: MovieRepositoryImpl
    @MockK lateinit var service: MovieService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = MovieRepositoryImpl(service)
    }

    @Test
    fun getMovies_callsService() = runBlocking {
        val response = NetworkResponse.Success(MovieDataFactory.makeMovieResponse())
        coEvery { service.getMovies(any()) } returns response

        repository.getMoviesSuccess()

        coVerify(exactly = 1) {
            service.getMovies(any())
        }
    }

    @Test
    fun getMovies_returnsSuccessData() = runBlocking {
        val response = NetworkResponse.Success(MovieDataFactory.makeMovieResponse())
        coEvery { service.getMovies(any()) } returns response

        val result = repository.getMoviesSuccess()

        val expectedResult = Result.Success(response.body.movies)
        assertReflectionEquals(expectedResult, result)
    }

    @Test
    fun getMovies_returnsApiErrorData() = runBlocking {
        val response = NetworkResponse.ApiError(MovieDataFactory.makeMovieError(), 7)
        coEvery { service.getMovies(any()) } returns response

        val result = repository.getMoviesFailure()

        val expectedResult = Result.Error(response.body.statusMessage)
        assertReflectionEquals(expectedResult, result)
    }

    @Test
    fun getMovies_returnsNetworkErrorData() = runBlocking {
        val response = NetworkResponse.NetworkError(IOException("${UUID.randomUUID()}"))
        coEvery { service.getMovies(any()) } returns response

        val result = repository.getMoviesFailure()

        val expectedResult = Result.Error(response.error.localizedMessage)
        assertReflectionEquals(expectedResult, result)
    }

    @Test
    fun getMovies_returnsUnknownErrorData() = runBlocking {
        val response = NetworkResponse.UnknownError(RuntimeException("${UUID.randomUUID()}"))
        coEvery { service.getMovies(any()) } returns response

        val result = repository.getMoviesFailure()

        val expectedResult = Result.Error(response.error?.localizedMessage)
        assertReflectionEquals(expectedResult, result)
    }
}