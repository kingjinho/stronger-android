package com.fitness.stronger.android.screens.compose.main.camera

import android.content.ContentValues
import android.icu.text.SimpleDateFormat
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.fitness.stronger.android.R
import com.fitness.stronger.android.data.ECameraMode
import com.fitness.stronger.android.data.ECameraType
import com.fitness.stronger.android.data.ECameraViewRecomposition
import com.fitness.stronger.android.data.LineData
import com.fitness.stronger.android.utils.ext.drawLine
import java.util.Locale

private const val FILE_PATH = "Pictures/stronger"
private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val MIME_TYPE = "image/jpeg"

@Composable
fun ScreenCameraView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    val cameraSelectors = mapOf(
        ECameraType.FRONT to CameraSelector.DEFAULT_FRONT_CAMERA,
        ECameraType.BACK to CameraSelector.DEFAULT_BACK_CAMERA
    )

    var cameraProvider: ProcessCameraProvider? by remember {
        mutableStateOf(null)
    }

    val previewUseCase = androidx.camera.core.Preview.Builder()
        .build()

    val imageCaptureUseCase = ImageCapture.Builder().build()
    val recorder = Recorder.Builder()
        .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
        .build()
    val videoOutput = VideoCapture.withOutput(recorder)

    var cameraMode by remember {
        mutableStateOf(ECameraMode.PHOTO)
    }

    var isDrawingEnabled by remember {
        mutableStateOf(false)
    }

    var cameraType by remember {
        mutableStateOf(ECameraType.FRONT)
    }

    var recompositionDueTo by remember {
        mutableStateOf(ECameraViewRecomposition.NONE)
    }

    val view = LocalView.current

    Column(modifier = modifier.fillMaxSize()) {
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
                        id = R.id.camera_preview
                        scaleType = PreviewView.ScaleType.FILL_START
                    }.also { cameraView ->
                        cameraProviderFuture.addListener({
                            cameraProvider = cameraProviderFuture.get()

                            val cameraSelector = if (canUseFrontCamera(cameraProvider!!)) {
                                cameraSelectors[ECameraType.FRONT]
                            } else {
                                cameraSelectors[ECameraType.BACK]
                            }

                            previewUseCase.also {
                                it.setSurfaceProvider(cameraView.surfaceProvider)
                            }

                            try {
                                cameraProvider!!.unbindAll()
                                bindToCameraLifecycle(
                                    cameraProvider,
                                    lifecycleOwner,
                                    cameraSelector,
                                    previewUseCase
                                )
                            } catch (_: Exception) {

                            }
                        }, ContextCompat.getMainExecutor(context))
                    }
                },
                //should only be called when it comes to camera switching
                update = { cameraView ->
                    if (cameraProvider == null) {
                        return@AndroidView
                    }
                    if (recompositionDueTo == ECameraViewRecomposition.LINE) {
                        return@AndroidView
                    }

                    try {
                        cameraProvider!!.unbindAll()

                        val cameraSelector =
                            if (canUseFrontCamera(cameraProvider!!) && isCameraFacingFront(
                                    cameraType
                                )
                            ) {
                                cameraSelectors[ECameraType.FRONT]
                            } else {
                                cameraSelectors[ECameraType.BACK]
                            }

                        previewUseCase.also {
                            it.setSurfaceProvider(cameraView.surfaceProvider)
                        }

                        bindToCameraLifecycle(
                            cameraProvider,
                            lifecycleOwner,
                            cameraSelector,
                            previewUseCase
                        )
                    } catch (e: IllegalStateException) {
                        e.printStackTrace()
                    }
                })
            if (isDrawingEnabled) {
                DrawingCanvas(
                    modifier = modifier.fillMaxSize(),
                )
            }
        }

        Row(
            modifier = modifier
                .weight(3f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    id = if (isDrawingEnabled) {
                        R.drawable.ic_draw_disabled_24
                    } else {
                        R.drawable.ic_draw_enabled_24
                    }
                ),
                contentDescription = stringResource(
                    id = if (isDrawingEnabled) {
                        R.string.content_desc_draw_line_enabled
                    } else {
                        R.string.content_desc_draw_line_disabled
                    }
                ),
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        isDrawingEnabled = !isDrawingEnabled
                        recompositionDueTo = ECameraViewRecomposition.LINE
                    }
            )

            Button(
                onClick = {
                    val cameraView = view.findViewById<View>(R.id.camera_preview)
                    when (cameraMode) {
                        ECameraMode.PHOTO -> {
                            val name = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
                                .format(System.currentTimeMillis())
                            val contentValues = ContentValues().apply {
                                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                                put(MediaStore.MediaColumns.MIME_TYPE, MIME_TYPE)
                                put(MediaStore.Images.Media.RELATIVE_PATH, FILE_PATH)
                            }

                            val outputOptions = ImageCapture.OutputFileOptions
                                .Builder(
                                    context.contentResolver,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    contentValues
                                )
                                .build()

                            imageCaptureUseCase.takePicture(
                                outputOptions,
                                ContextCompat.getMainExecutor(context),
                                object : ImageCapture.OnImageSavedCallback {
                                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onError(exception: ImageCaptureException) {
                                        TODO("Not yet implemented")
                                    }
                                }
                            )
                        }

                        ECameraMode.VIDEO -> {

                        }
                    }
                },
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            ) {
                Text("test")
            }

            Image(
                painter = painterResource(
                    id = R.drawable.ic_switch_camera_20_18
                ),
                contentDescription = stringResource(
                    id = if (cameraType == ECameraType.FRONT) {
                        R.string.content_desc_switch_camera_to_back
                    } else {
                        R.string.content_desc_switch_camera_to_front
                    }
                ),
                modifier = Modifier
                    .size(40.dp, 36.dp)
                    .clickable {
                        cameraType = if (cameraType == ECameraType.FRONT) {
                            ECameraType.BACK
                        } else {
                            ECameraType.FRONT
                        }
                        recompositionDueTo = ECameraViewRecomposition.CAMERA_SWITCH
                        isDrawingEnabled = false
                    }
            )
        }
    }
}

private fun bindToCameraLifecycle(
    cameraProvider: ProcessCameraProvider?,
    lifecycleOwner: LifecycleOwner,
    cameraSelector: CameraSelector?,
    preview: androidx.camera.core.Preview
) {
    cameraProvider!!.bindToLifecycle(
        lifecycleOwner,
        cameraSelector!!,
        preview
    )
}

private fun canUseFrontCamera(
    cameraProvider: ProcessCameraProvider,
): Boolean {
    return cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)
}

private fun isCameraFacingFront(cameraType: ECameraType): Boolean {
    return cameraType == ECameraType.FRONT
}


@Composable
fun DrawingCanvas(modifier: Modifier = Modifier) {
    val lines = remember {
        mutableStateListOf<LineData>()
    }
    Canvas(
        modifier = modifier
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