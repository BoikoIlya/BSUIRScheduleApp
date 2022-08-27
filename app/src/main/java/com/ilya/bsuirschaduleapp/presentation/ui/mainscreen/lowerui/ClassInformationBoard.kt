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
import com.ilya.bsuirschaduleapp.data.network.dto.Schedule
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*

@Composable
fun ClassInformationBoard(
    lessonInfo: Schedule
){
        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    when (lessonInfo.lessonTypeAbbrev) {
                        stringResource(R.string.lecture) -> VeryLightGreen
                        stringResource(R.string.lab) -> VeryLightRed
                        stringResource(R.string.practical) -> VeryLightPurple
                        else -> Color.LightGray
                    }
                )
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Column() {
                Text(
                    text = lessonInfo.startLessonTime +" - "+lessonInfo.endLessonTime,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    color = Color.Gray
                )
                Text(
                    text = lessonInfo.subject+"("+lessonInfo.lessonTypeAbbrev+")",
                    style = MaterialTheme.typography.h3,
                    color = Color.Black
                )
                Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        text = if(lessonInfo.employees!=null && lessonInfo.employees.isNotEmpty()){
                        lessonInfo.employees[0].lastName+
                                " "+lessonInfo.employees[0].firstName[0]+"."+
                        " "+lessonInfo.employees[0].middleName[0]+"." +
                                if(lessonInfo.numSubgroup!=0L) " (подгр. ${lessonInfo.numSubgroup})"
                            else ""
                        }
                        else stringResource(R.string.group_shortcut) + lessonInfo.studentGroups[0].name +
                                if(lessonInfo.studentGroups.size>1)
                                   ", "+ lessonInfo.studentGroups[1].name+"..."
                        else " "
                        ,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,

                    )
                    Text(
                        text = if(lessonInfo.auditories!!.isNotEmpty())
                                lessonInfo.auditories[0]
                                else "",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(
                                when (lessonInfo.lessonTypeAbbrev) {
                                    stringResource(R.string.lecture) -> Green
                                    stringResource(R.string.lab) -> Red
                                    stringResource(R.string.practical) -> Purple
                                    else -> Color.LightGray
                                }
                            )
                            .padding(5.dp)

                    )
                }
                    if(lessonInfo.note!=null) {
                        Text(
                            text = lessonInfo.note,
                            fontSize = MaterialTheme.typography.body1.fontSize,
                            color = Color.Gray,
                        )
                    }
                }
            }
        }
}