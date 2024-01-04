package com.fitness.stronger.android.screens.main.camera

import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ScreenCameraView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        AndroidView(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    setBackgroundColor(Color.BLACK)
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also {
                    it.controller = cameraController
                    cameraController.unbind()
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            })
    }
}

@Preview
@Composable
fun ScreenCameraViewPreview() {
    ScreenCameraView()
}

@Preview(locale = "ko")
@Composable
fun ScreenCameraViewPreviewKO() {
    ScreenCameraView()
}