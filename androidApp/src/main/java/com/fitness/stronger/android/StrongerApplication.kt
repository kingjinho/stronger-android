package com.fitness.stronger.android

import android.app.Application
import android.content.SharedPreferences
import com.fitness.stronger.Const
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StrongerApplication : Application() {

    val sharedPref: SharedPreferences by lazy {
        getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)
    }

    override fun onCreate() {
        super.onCreate()
    }
}