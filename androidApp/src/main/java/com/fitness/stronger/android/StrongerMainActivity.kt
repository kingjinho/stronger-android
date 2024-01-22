package com.fitness.stronger.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.fitness.stronger.android.utils.ext.getSharedPref

class StrongerMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_stronger)
    }

//    @OptIn(ExperimentalPermissionsApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            StrongerApplicationTheme {
//                val navController = rememberNavController()
//                val cameraPermission =
//                    rememberPermissionState(permission = Manifest.permission.CAMERA) {
//                        if (it) {
//                            navController.navigate(Route.CameraView.name)
//                        }
//                    }
//
//                NavHost(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background),
//                    navController = navController,
//                    startDestination = if (isInitialAppOpen()) {
//                        setInitialAppOpenToFalse()
//                        Route.Onboarding.name
//                    } else {
//                        Route.Main.name
//                    }
//                ) {
//
//                    composable(Route.Onboarding.name) {
//                        ScreenOnBoarding {
//                            if (cameraPermission.status.isGranted) {
//                                navController.run {
//                                    popBackStack(Route.Onboarding.name, inclusive = true)
//                                    navigate(Route.Main.name)
//                                }
//                            } else {
//                                cameraPermission.launchPermissionRequest()
//                            }
//                        }
//                    }
//
//                    composable(Route.Main.name) {
//                        ScreenMain(
//                            onStartCameraViewClick = {
//                                if (cameraPermission.status.isGranted) {
//                                    navController.navigate(Route.CameraView.name)
//                                } else {
//                                    cameraPermission.launchPermissionRequest()
//                                }
//                            }
//                        )
//                    }
//
//                    composable(Route.CameraView.name) {
//                        ScreenCameraView()
//                    }
//                }
//            }
//        }
//    }

    private fun isInitialAppOpen() =
        application.getSharedPref().getBoolean(Const.PREF_KEY_IS_INITIAL_OPEN, true)

    private fun setInitialAppOpenToFalse() =
        application.getSharedPref().edit(true) {
            putBoolean(Const.PREF_KEY_IS_INITIAL_OPEN, false)
        }
}
