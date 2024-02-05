//package com.fitness.stronger.android.screens.compose.onboarding
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.PlatformTextStyle
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.fitness.stronger.android.R
//
//@Composable
//fun ScreenOnBoardingPermission(
//    modifier: Modifier = Modifier,
//    onClickAllow: () -> Unit = {}
//) {
//    Column(
//        modifier = modifier.padding(horizontal = 20.dp, vertical = 48.dp)
//    ) {
//        Text(
//            text = stringResource(id = R.string.msg_onboarding_permission_headline),
//            fontFamily = FontFamily(Font(R.font.sf_pro)),
//            fontSize = 40.sp,
//            fontWeight = FontWeight.W600,
//            color = Color.White,
//            style = TextStyle(
//                fontSize = 40.sp
//            )
//        )
//
//        Spacer(modifier = modifier.height(16.dp))
//
//        Text(
//            text = stringResource(id = R.string.msg_onboarding_permission_description),
//            fontFamily = FontFamily(Font(R.font.sf_pro)),
//            fontSize = 16.sp,
//            fontWeight = FontWeight.W400,
//            color = Color(0xff8e8e90),
//            style = TextStyle(
//                fontSize = 16.sp
//            )
//        )
//
//        Spacer(modifier = modifier.height(48.dp))
//
//        Column {
//            Row(
//                modifier = modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_onboarding_permission_camera),
//                    contentDescription = stringResource(id = R.string.content_desc_onboarding_permission_camera),
//                )
//
//                Text(
//                    text = stringResource(id = R.string.msg_onboarding_permission_camera_needed),
//                    fontFamily = FontFamily(Font(R.font.sf_pro)),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.W400,
//                    color = Color.White,
//                    style = TextStyle(
//                        platformStyle = PlatformTextStyle(
//                            includeFontPadding = false
//                        )
//                    )
//                )
//            }
//        }
//
//        Spacer(modifier = modifier.weight(1f))
//
//        Button(
//            modifier = modifier
//                .fillMaxWidth(),
//            onClick = onClickAllow
//        ) {
//            Text(text = stringResource(id = R.string.msg_btn_onboarding_permission_allow))
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun ScreenOnBoardingPermissionPreview() {
//    ScreenOnBoardingPermission()
//}
//
//@Preview(locale = "ko")
//@Composable
//fun ScreenOnBoardingPermissionPreviewKR() {
//    ScreenOnBoardingPermission()
//}