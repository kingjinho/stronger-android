package com.fitness.stronger.android.screens.compose.navigation

import com.fitness.stronger.android.Const


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