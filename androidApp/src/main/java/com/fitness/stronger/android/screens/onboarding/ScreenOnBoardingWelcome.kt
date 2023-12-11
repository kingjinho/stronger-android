package com.fitness.stronger.android.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitness.stronger.android.R

@Composable
fun ScreenOnBoardingWelcome(
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit = {},
) {
    Column(modifier = modifier.padding(horizontal = 20.dp, vertical = 48.dp)) {

        Text(
            text = stringResource(id = R.string.msg_onboarding_welcome_headline),
            color = Color.White,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.sf_pro)),
                fontSize = 40.sp,
                fontWeight = FontWeight.W600,
            )
        )
        Spacer(modifier = Modifier.height(56.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_workout_onboarding),
                contentDescription = stringResource(id = R.string.content_desc_onboarding_workout_feature_draw_line),
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = stringResource(id = R.string.msg_title_onboarding_workout_feature_draw_line),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                        .copy(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                            fontWeight = FontWeight.W600,
                        )
                )

                Text(
                    text = stringResource(id = R.string.msg_description_onboarding_workout_feature_draw_line),
                    fontFamily = FontFamily(Font(R.font.sf_pro)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xff8e8e90),
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onClickNext,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.msg_btn_start_working_out)
            )
        }
    }
}

@Preview
@Composable
fun ScreenOnBoardingExplanationPreview() {
    ScreenOnBoardingWelcome()
}

@Preview(locale = "ko")
@Composable
fun ScreenOnBoardingExplanationPreviewKR() {
    ScreenOnBoardingWelcome()
}