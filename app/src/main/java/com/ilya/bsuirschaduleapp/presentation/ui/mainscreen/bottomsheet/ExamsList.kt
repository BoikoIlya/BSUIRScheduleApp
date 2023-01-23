package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.ClassItem
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import com.ilya.bsuirschaduleapp.utils.Constance

/**
 * Created by HP on 22.01.2023.
 **/

@Composable
fun ShowExams(
    exams: MutableState<List<ScheduleDomain.Schedule>>
){

Column(modifier = Modifier
    .fillMaxSize()
    .background(BsuirScheduleAppTheme.colors.AppPrimary)
    ) {
        if (!exams.value.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BsuirScheduleAppTheme.colors.AppPrimary)
                    .padding(horizontal = 10.dp)
            ) {
                items(exams.value) {
                    Spacer(modifier = Modifier.height(15.dp))
                    ClassItem(lessonInfo = it)

                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.owner),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = BsuirScheduleAppTheme.colors.LowerUiTextColor)
                )
                Text(
                    text = "Экзаменов пока нет",
                    style = Typography.h2,
                    color = BsuirScheduleAppTheme.colors.LowerUiTextColor
                )
            }
        }
    }
}

