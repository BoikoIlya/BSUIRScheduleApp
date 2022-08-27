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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.data.network.dto.Schedule
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkGrayForAlert
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LessonInfoAlert(
    showAlert: MutableState<Boolean>,
    lessonInfo: Schedule
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
            title = { Text(text =lessonInfo.subjectFullName ) },
            text = {
                Row {
                    GlideImage(
                        imageModel =  lessonInfo.employees!![0].photoLink,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .size(130.dp)
                        ,
                        contentScale = ContentScale.Fit)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = lessonInfo.employees[0].lastName+" "+
                            lessonInfo.employees[0].firstName +" "+
                            lessonInfo.employees[0].middleName,
                    )
                }
            }
        )
    }
}