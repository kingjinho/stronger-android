package com.fitness.stronger.android.utils.ext

import android.app.Application
import android.content.SharedPreferences
import com.fitness.stronger.android.StrongerApplication

fun Application.getSharedPref(): SharedPreferences {
    return (this as StrongerApplication).sharedPref
}