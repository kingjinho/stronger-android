package com.fitness.stronger.android.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fitness.stronger.android.R

@Composable
fun ScreenMain(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.img_screen_main_workout),
            contentDescription = stringResource(id = R.string.content_desc_screen_main_image)
        )
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