package com.fitness.stronger.android.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.stronger.android.R

@Composable
fun ScreenMain(
    modifier: Modifier = Modifier,
    onStartCameraViewClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.img_screen_main_workout),
            contentDescription = stringResource(id = R.string.content_desc_screen_main_image)
        )

        Spacer(modifier = modifier.weight(1f))

        Button(
            onClick = onStartCameraViewClick,
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = stringResource(R.string.msg_btn_screen_main_start_working_out))
        }
    }
}

@Preview
@Composable
fun ScreenMainPreview() {
    ScreenMain()
}

@Preview(locale = "ko")
@Composable
fun ScreenMainPreviewKR() {
    ScreenMain()
}