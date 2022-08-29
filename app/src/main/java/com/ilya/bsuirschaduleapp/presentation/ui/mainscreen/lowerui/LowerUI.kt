@file:OptIn(ExperimentalPagerApi::class)

package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.data.network.dto.Schedule
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.ClassItem
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkSea
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.utils.Constance


@Composable
fun LowerUI(
    viewModel: MainViewModel = hiltViewModel(),
    selectedDayOfCurrentWeek: MutableState<Int>,
    selectedWeek: MutableState<Int>,
) {

        val scheduleState = viewModel.schedule.collectAsState()
        val selectedSubGroup = viewModel.selectedSubGroup.collectAsState()
        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 45.dp,
                        topEnd = 44.dp
                    )
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp
                ),
        ) {
            Column(
            ) {
                Text(
                    text = stringResource(R.string.lessons),
                    style = MaterialTheme.typography.h2,
                    color = Color.Black
                )
                 val schedule = when(selectedDayOfCurrentWeek.value){
                         0->viewModel.schedule.collectAsState().value.data.schedules.Monday
                         1->viewModel.schedule.collectAsState().value.data.schedules.Tuesday
                         2->viewModel.schedule.collectAsState().value.data.schedules.Wednesday
                         3->viewModel.schedule.collectAsState().value.data.schedules.Thursday
                         4->viewModel.schedule.collectAsState().value.data.schedules.Friday
                         5->viewModel.schedule.collectAsState().value.data.schedules.Saturday
                     else->viewModel.schedule.collectAsState().value.data.schedules.Saturday
                 }
                if (!scheduleState.value.isLoading) {
                    if (schedule != null) {
                        LazyColumn{
                            items(schedule) {
                                if (it.weekNumber.contains(selectedWeek.value.toLong())
                                ) {
                                    if (selectedSubGroup.value.toLong() == Constance.ALL_GROUPS) {
                                        ClassItem(lessonInfo = it)
                                        Spacer(modifier = Modifier.height(15.dp))
                                    } else if (it.numSubgroup == Constance.ALL_GROUPS ||
                                        it.numSubgroup == selectedSubGroup.value.toLong()
                                    ) {
                                        ClassItem(lessonInfo = it)
                                        Spacer(modifier = Modifier.height(15.dp))
                                    }
                                }
                            }
                        }
                    }

                } else {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter) {
                        CircularProgressIndicator(color = DarkSea)
                    }
                }
            }
        }
    }
