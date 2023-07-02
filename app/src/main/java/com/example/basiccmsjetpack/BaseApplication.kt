package com.example.basiccmsjetpack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
/*
* if BuildConfig.DEBUG shows error add the code below in build.gradle(:app)
* android {
    buildFeatures {
        buildConfig = true
    }
}
* */
