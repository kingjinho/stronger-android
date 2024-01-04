package com.fitness.stronger.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StrongerApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
    } else {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            secondary = Color(0xFF03DAC5),
            tertiary = Color(0xFF3700B3)
        )
//        lightColorScheme(
//            primary = Color(0xFF6200EE),
//            secondary = Color(0xFF03DAC5),
//            tertiary = Color(0xFF3700B3)
//        )
    }
    val typography = Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
        ),

        displayMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
        ),

        displaySmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
        ),

        headlineLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),

        headlineMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),

        headlineSmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
        ),

        titleLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),

        titleMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
        ),

        titleSmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),

        labelLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
        ),

        labelMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),

        labelSmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W500,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
        ),

        bodyLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),

        bodyMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
        ),

        bodySmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf_pro)),
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
        ),
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
