package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkGrayForAlert
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LessonInfoAlert(
    showAlert: MutableState<Boolean>,
    lessonInfo: ScheduleDomain.Schedule
){
    if (showAlert.value) {
        AlertDialog(
            backgroundColor = DarkGrayForAlert,
            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        showAlert.value = false
                    }) {
                    Text(text = stringResource(R.string.ok), color = Color.Black)
                }
            },
            onDismissRequest = { },
            title = { Text(text =lessonInfo.subjectFullName ,color = Color.LightGray, style = Typography.h4) },
            text = {
                Row {
                    GlideImage(
                        error = painterResource(id = R.drawable.ic_person),
                        imageModel =  lessonInfo.employeePhotoLink,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .size(130.dp)
                        ,
                        contentScale = ContentScale.Fit)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = lessonInfo.employees,
                        color = Color.LightGray,
                        style = Typography.body1
                    )
                }
            }
        )
    }
}
