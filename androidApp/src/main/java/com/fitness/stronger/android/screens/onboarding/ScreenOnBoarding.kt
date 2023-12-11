package com.fitness.stronger.android.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenOnBoarding(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            2
        })
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(modifier = modifier, state = pagerState) { page ->
        when (page) {
            0 -> ScreenOnBoardingWelcome {
                coroutineScope.launch {
                    pagerState.scrollToPage(1)
                }
            }

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