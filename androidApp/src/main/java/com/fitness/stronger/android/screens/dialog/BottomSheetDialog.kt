package com.fitness.stronger.android.screens.dialog

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun BottomSheetDialog(
    content: @Composable () -> Unit
) {
    BackHandler {

    }
    content()
}