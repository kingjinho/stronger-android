package com.fitness.stronger.android.screens.main.camera

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.fitness.stronger.android.R
import com.fitness.stronger.android.data.ECameraType
import com.fitness.stronger.android.data.LineData
import com.fitness.stronger.android.utils.ext.drawLine

@Composable
fun ScreenCameraView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    var cameraProvider: ProcessCameraProvider? by remember {
        mutableStateOf(null)
    }

    val isDrawingEnabled = remember {
        mutableStateOf(false)
    }

    val cameraType = remember {
        mutableStateOf(ECameraType.FRONT)
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(
                    id = if (isDrawingEnabled.value) {
                        R.drawable.ic_draw_disabled_24
                    } else {
                        R.drawable.ic_draw_enabled_24
                    }
                ),
                contentDescription = stringResource(
                    id = if (isDrawingEnabled.value) {
                        R.string.content_desc_draw_line_enabled
                    } else {
                        R.string.content_desc_draw_line_disabled
                    }
                ),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        isDrawingEnabled.value = !isDrawingEnabled.value
                    }
            )

            Image(
                painter = painterResource(
                    id = R.drawable.ic_switch_camera_20_18
                ),
                contentDescription = stringResource(
                    id = if (cameraType.value == ECameraType.FRONT) {
                        R.string.content_desc_switch_camera_to_back
                    } else {
                        R.string.content_desc_switch_camera_to_front
                    }
                ),
                modifier = Modifier
                    .size(40.dp, 36.dp)
                    .clickable {
                        cameraType.value = if (cameraType.value == ECameraType.FRONT) {
                            ECameraType.BACK
                        } else {
                            ECameraType.FRONT
                        }
                    }
            )
        }
        Scaffold(modifier = modifier.weight(6f)) { paddingValues ->
            AndroidView(
                modifier = modifier
                    .padding(paddingValues),
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { cameraView ->
                        cameraProviderFuture.addListener({
                            cameraProvider = cameraProviderFuture.get()

                            val cameraSelector =
                                if (cameraProvider!!.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
                                    && cameraType.value == ECameraType.FRONT
                                ) {
                                    CameraSelector.DEFAULT_FRONT_CAMERA
                                } else {
                                    CameraSelector.DEFAULT_BACK_CAMERA
                                }

                            val preview = androidx.camera.core.Preview.Builder()
                                .build()
                                .also {
                                    it.setSurfaceProvider(cameraView.surfaceProvider)
                                }

                            try {
                                cameraProvider!!.unbindAll()
                                cameraProvider!!.bindToLifecycle(
                                    lifecycleOwner,
                                    cameraSelector,
                                    preview
                                )
                            } catch (_: Exception) {

                            }

                        }, ContextCompat.getMainExecutor(context))
                    }
                },
                update = { cameraView ->
                    try {
                        cameraProvider?.unbindAll()

                        val cameraSelector =
                            if (cameraProvider!!.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
                                && cameraType.value == ECameraType.FRONT
                            ) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }

                        val preview = androidx.camera.core.Preview.Builder()
                            .build()
                            .also {
                                it.setSurfaceProvider(cameraView.surfaceProvider)
                            }

                        cameraProvider?.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                })
            if (isDrawingEnabled.value) {
                DrawingComposable(
                    modifier = modifier.fillMaxSize(),
                )
            }
        }

        Row(modifier = modifier.weight(3f)) {
            Button(onClick = { /*TODO*/ }) {
                Text("test")
            }
        }
    }
}

@Composable
fun DrawingComposable(modifier: Modifier = Modifier) {
    val lines = remember {
        mutableStateListOf<LineData>()
    }
    Canvas(modifier = modifier
        .pointerInput(true) {
            detectDragGestures { change, dragAmount ->
                change.consume()

                val line = LineData(
                    start = change.position - dragAmount,
                    end = change.position
                )

                lines.add(line)
            }
        }) {
        lines.forEach {
            drawLine(it)
        }
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