package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

@Composable
fun ClassInformationBoard(
    lessonInfo: ScheduleDomain.Schedule
){
        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    when (lessonInfo.lessonTypeAbbrev) {
                        stringResource(R.string.lecture) -> BsuirScheduleAppTheme.colors.LowerUiLecturesColorPrimary
                        stringResource(R.string.lab) -> BsuirScheduleAppTheme.colors.LowerUiLabsLessonsColorPrimary
                        stringResource(R.string.practical) -> BsuirScheduleAppTheme.colors.LowerUiPracticalLessonsColorPrimary
                        else -> Color.LightGray
                    }
                )
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Column() {
                Text(
                    text = lessonInfo.startLessonTime +" - "+lessonInfo.endLessonTime,
                    style = Typography.body1,
                    color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorSecondary
                )
                Text(
                    text = lessonInfo.subject+"("+lessonInfo.lessonTypeAbbrev+")",
                    style = Typography.h2,
                    color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorPrimary
                )
                Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = Typography.body1,
                        text = if(lessonInfo.employeeFio.isNotEmpty()) lessonInfo.employeeFio
                        else lessonInfo.studentGroups
                        ,
                        color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorSecondary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                    )
                    Text(
                        text = lessonInfo.auditories,
                        style = Typography.h4,
                        color = Color.White,
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(
                                when (lessonInfo.lessonTypeAbbrev) {
                                    stringResource(R.string.lecture) -> BsuirScheduleAppTheme.colors.LowerUiLecturesColorSecondary
                                    stringResource(R.string.lab) -> BsuirScheduleAppTheme.colors.LowerUiLabsLessonsColorSecondary
                                    stringResource(R.string.practical) -> BsuirScheduleAppTheme.colors.LowerUiPracticalLessonsColorSecondary
                                    else -> Color.LightGray
                                }
                            )
                            .padding(5.dp)

                    )
                }
                    if(lessonInfo.note.isNotEmpty()) {
                        Text(
                            text = lessonInfo.note,
                            style = Typography.body1,
                            color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorSecondary,
                        )
                    }
                }
            }
        }
}