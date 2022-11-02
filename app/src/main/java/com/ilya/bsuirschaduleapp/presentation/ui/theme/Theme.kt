package com.ilya.bsuirschaduleapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = BsuirScheduleAppColors(
    AppPrimary = Color(0xFF334756),//Fog,
    UpperUiPrimary = Color(0xFF082032) ,//DarkSea,
    UpperUiSecondary = Color(0xFF2C394B),//LighterDarkSea,
    UpperUiSelection = LightGray,
    StaticTextColor = LightGray,
    LowerUiTextColor = White,
    LowerUiLessonsTextColorPrimary = White,
    LowerUiLessonsTextColorSecondary = LightGray,
    LowerUiLecturesColorPrimary = Color(0xFF347474),
    LowerUiLecturesColorSecondary = Color(0xFF0F3D3E),
    LowerUiPracticalLessonsColorPrimary =Color(0xFF712B75),
    LowerUiPracticalLessonsColorSecondary = Color(0xFF46244C),
    LowerUiLabsLessonsColorPrimary = Color(0xFF821e3c),
    LowerUiLabsLessonsColorSecondary = Color(0xFF420516),
    IconTint = LightGray,
)

private val LightColorPalette = BsuirScheduleAppColors(
    AppPrimary = White,
    UpperUiPrimary =  Sea,
    UpperUiSecondary = LightSea,
    UpperUiSelection = White,
    StaticTextColor = White,
    LowerUiTextColor = Black,
    LowerUiLessonsTextColorPrimary = Black,
    LowerUiLessonsTextColorSecondary = Gray,
    LowerUiLecturesColorPrimary = VeryLightGreen,
    LowerUiLecturesColorSecondary = Green,
    LowerUiPracticalLessonsColorPrimary =VeryLightPurple,
    LowerUiPracticalLessonsColorSecondary = Purple,
    LowerUiLabsLessonsColorPrimary = VeryLightRed,
    LowerUiLabsLessonsColorSecondary = Red,
    IconTint = White,
)

@Composable
fun BSUIRSchaduleAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        //LightColorPalette
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalBsuirScheduleAppColors provides colors,
        content = content
    )
//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
}

