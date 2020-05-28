package com.example.retrofit2coroutinescalladapter.di

import com.example.retrofit2coroutinescalladapter.data.network.MovieService
import com.example.retrofit2coroutinescalladapter.repository.MovieRepository
import com.example.retrofit2coroutinescalladapter.repository.MovieRepositoryImpl
import com.example.retrofit2coroutinescalladapter.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // MoviesRepository instance
    single<MovieRepository> { provideMovieRepositoryImpl(get()) }
    // MainViewModel instance
    viewModel { provideMainViewModel(get()) }
}

private fun provideMovieRepositoryImpl(movieService: MovieService): MovieRepositoryImpl {
    return MovieRepositoryImpl(movieService)
}

private fun provideMainViewModel(repository: MovieRepository): MainViewModel {
    return MainViewModel(repository)
}