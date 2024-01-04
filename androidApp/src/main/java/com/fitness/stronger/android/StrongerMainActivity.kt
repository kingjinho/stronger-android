package com.fitness.stronger.android

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fitness.stronger.android.screens.main.ScreenMain
import com.fitness.stronger.android.screens.main.camera.ScreenCameraView
import com.fitness.stronger.android.screens.navigation.Route
import com.fitness.stronger.android.screens.onboarding.ScreenOnBoarding
import com.fitness.stronger.android.utils.ext.getSharedPref
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class StrongerMainActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrongerApplicationTheme {
                val navController = rememberNavController()
                val cameraPermission =
                    rememberPermissionState(permission = Manifest.permission.CAMERA) {
                        if (it) {
                            navController.navigate(Route.CameraView.name)
                        }
                    }

                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    navController = navController,
                    startDestination = if (isInitialOpen()) {
                        Route.Onboarding.name
                    } else {
                        Route.Main.name
                    }
                ) {

                    composable(Route.Onboarding.name) {
                        ScreenOnBoarding {
                            if (cameraPermission.status.isGranted) {
                                navController.run {
                                    popBackStack(Route.Onboarding.name, inclusive = true)
                                    navigate(Route.Main.name)
                                }
                            } else {
                                cameraPermission.launchPermissionRequest()
                            }
                        }
                    }

                    composable(Route.Main.name) {
                        ScreenMain(
                            onStartCameraViewClick = {
                                if (cameraPermission.status.isGranted) {
                                    navController.navigate(Route.CameraView.name)
                                } else {
                                    cameraPermission.launchPermissionRequest()
                                }
                            }
                        )
                    }

                    composable(Route.CameraView.name) {
                        ScreenCameraView()
                    }
                }
            }
        }
    }

    private fun isInitialOpen() =
        application.getSharedPref().getBoolean(Const.PREF_KEY_IS_INITIAL_OPEN, true)

}
