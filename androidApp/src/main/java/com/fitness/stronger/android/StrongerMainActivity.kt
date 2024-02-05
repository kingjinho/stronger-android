package com.fitness.stronger.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.fitness.stronger.android.databinding.ActivityMainStrongerBinding
import com.fitness.stronger.android.screens.compose.navigation.Route
import com.fitness.stronger.android.screens.view_system.main.ScreenMainView
import com.fitness.stronger.android.screens.view_system.main.camera.ScreenCameraView
import com.fitness.stronger.android.screens.view_system.onboarding.ScreenOnboardingView
import com.fitness.stronger.android.utils.ext.getSharedPref

class StrongerMainActivity : AppCompatActivity() {

    private var _binding: ActivityMainStrongerBinding? = null
    private val binding: ActivityMainStrongerBinding
        get() = _binding!!

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment)
            .navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainStrongerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNavGraph()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun createNavGraph() {
        val startDestination = getStartDestinationByInitialOpenStatus()
        navController.graph = navController.createGraph(
            startDestination
        ) {
            fragment<ScreenMainView>(Route.Main.name)
            fragment<ScreenOnboardingView>(Route.Onboarding.name)
            fragment<ScreenCameraView>(Route.CameraView.name)
        }
    }

    private fun isInitialAppOpen() =
        application.getSharedPref().getBoolean(Const.PREF_KEY_IS_INITIAL_OPEN, true)

    private fun setInitialAppOpenToFalse() =
        application.getSharedPref().edit(true) {
            putBoolean(Const.PREF_KEY_IS_INITIAL_OPEN, false)
        }

    private fun getStartDestinationByInitialOpenStatus(): String {
        return if (isInitialAppOpen()) {
            setInitialAppOpenToFalse()
            Route.Onboarding.name
        } else {
            Route.Main.name
        }
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
}
