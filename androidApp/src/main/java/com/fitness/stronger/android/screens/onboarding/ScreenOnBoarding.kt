package com.fitness.stronger.android.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenOnBoarding() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            2
        })

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ScreenOnBoardingExplanation()
            1 -> ScreenOnBoardingPermission()
        }
    }
}

@Preview
@Composable
fun ScreenOnBoardingPreview() {
    ScreenOnBoarding()
}

@Preview(locale = "ko")
@Composable
fun ScreenOnBoardingPreviewKR() {
    ScreenOnBoarding()
}