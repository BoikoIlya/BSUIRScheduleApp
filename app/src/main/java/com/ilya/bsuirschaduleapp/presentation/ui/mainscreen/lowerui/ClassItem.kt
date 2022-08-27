package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.data.network.dto.Schedule
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.ClassInformationBoard
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.LessonInfoAlert
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.VerticalLine

@Composable
fun ClassItem(lessonInfo: Schedule){
    val showAlert = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.clickable {
            if(lessonInfo.employees?.isEmpty() == false)
            showAlert.value = true
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VerticalLine(lessonInfo.lessonTypeAbbrev)
        Spacer(modifier = Modifier.width(15.dp))
        ClassInformationBoard(lessonInfo)
        Spacer(modifier = Modifier.height(15.dp))
    }
    if(showAlert.value)
        LessonInfoAlert(showAlert = showAlert, lessonInfo = lessonInfo)
}

