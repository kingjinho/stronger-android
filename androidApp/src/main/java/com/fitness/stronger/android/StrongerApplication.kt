package com.fitness.stronger.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StrongerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}