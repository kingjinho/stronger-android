package com.fitness.stronger.android.screens.navigation

import com.fitness.stronger.Const

sealed class Route {
    abstract val name: String

    data object Main : Route() {
        override val name: String = Const.ROUTE_MAIN
    }

    data object Onboarding : Route() {
        override val name: String = Const.ROUTE_ONBOARDING
    }

    data object CameraView : Route() {
        override val name: String = Const.ROUTE_CAMERA_VIEW
    }

}