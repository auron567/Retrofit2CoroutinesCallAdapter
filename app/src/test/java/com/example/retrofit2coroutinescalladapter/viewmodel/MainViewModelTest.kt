package com.example.retrofit2coroutinescalladapter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.retrofit2coroutinescalladapter.data.model.Result
import com.example.retrofit2coroutinescalladapter.repository.MovieRepository
import com.example.retrofit2coroutinescalladapter.utils.CoroutineTestRule
import com.example.retrofit2coroutinescalladapter.utils.MovieDataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MainViewModel
    @MockK lateinit var repository: MovieRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getMoviesSuccess_returnsSuccess() = coroutineTestRule.runBlockingTest {
        val expectedResult = Result.Success(MovieDataFactory.makeMovieList())
        coEvery { repository.getMoviesSuccess() } returns expectedResult

        viewModel.getMoviesSuccess()

        coVerify(exactly = 1) {
            repository.getMoviesSuccess()
        }
        assertEquals(expectedResult, viewModel.moviesLiveData.value)
    }

    @Test
    fun getMoviesFailure_returnsError() = coroutineTestRule.runBlockingTest {
        val expectedResult = Result.Error("${UUID.randomUUID()}")
        coEvery { repository.getMoviesFailure() } returns expectedResult

        viewModel.getMoviesFailure()

        coVerify(exactly = 1) {
            repository.getMoviesFailure()
        }
        assertEquals(expectedResult, viewModel.moviesLiveData.value)
    }
}