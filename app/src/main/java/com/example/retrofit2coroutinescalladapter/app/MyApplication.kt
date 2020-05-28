package com.example.retrofit2coroutinescalladapter.app

import android.app.Application
import com.example.retrofit2coroutinescalladapter.BuildConfig
import com.example.retrofit2coroutinescalladapter.di.appModule
import com.example.retrofit2coroutinescalladapter.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, networkModule)
        }

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}