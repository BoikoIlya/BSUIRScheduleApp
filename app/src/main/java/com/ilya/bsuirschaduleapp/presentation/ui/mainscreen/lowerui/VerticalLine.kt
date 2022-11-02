package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Green
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Purple
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Red

@Composable
fun VerticalLine(
    lessonType: String,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(1000.dp))
                .width(7.dp)
                .height(100.dp)
                .background(
                    when (lessonType) {
                        stringResource(id = R.string.lecture) -> BsuirScheduleAppTheme.colors.LowerUiLecturesColorSecondary
                        stringResource(id = R.string.lab) -> BsuirScheduleAppTheme.colors.LowerUiLabsLessonsColorSecondary
                        stringResource(id = R.string.practical) -> BsuirScheduleAppTheme.colors.LowerUiPracticalLessonsColorSecondary
                        else -> Color.Gray
                    }
                ),
        )
    }
}

