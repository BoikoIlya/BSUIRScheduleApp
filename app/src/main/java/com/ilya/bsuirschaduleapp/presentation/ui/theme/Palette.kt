package com.ilya.bsuirschaduleapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Created by HP on 02.11.2022.
 **/

data class BsuirScheduleAppColors(
    val AppPrimary: Color,
    val UpperUiPrimary:Color,
    val UpperUiSecondary: Color,
    val UpperUiSelection: Color,
    val StaticTextColor: Color,
    val LowerUiTextColor: Color,
    val LowerUiLessonsTextColorPrimary: Color,
    val LowerUiLessonsTextColorSecondary: Color,
    //val LowerUiLessonsTextColorAuditory:Color,
    val LowerUiLecturesColorPrimary: Color,
    val LowerUiLecturesColorSecondary: Color,
    val LowerUiPracticalLessonsColorPrimary: Color,
    val LowerUiPracticalLessonsColorSecondary: Color,
    val LowerUiLabsLessonsColorPrimary: Color,
    val LowerUiLabsLessonsColorSecondary: Color,
    val IconTint:Color,
        )

object BsuirScheduleAppTheme{
    val colors: BsuirScheduleAppColors
    @Composable
    get() = LocalBsuirScheduleAppColors.current
}

val LocalBsuirScheduleAppColors = staticCompositionLocalOf<BsuirScheduleAppColors>{
    error("No colors provided")
}