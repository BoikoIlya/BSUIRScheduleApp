@file:OptIn(ExperimentalPagerApi::class)

package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.ClassItem
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkSea
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.utils.Constance


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LowerUI(
    viewModel: MainViewModel = hiltViewModel(),
    selectedDayOfCurrentWeek: MutableState<Int>,
    selectedWeek: MutableState<Int>,
    pagerState: PagerState
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
                    top = 15.dp
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                Row {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.lessons),
                        style = MaterialTheme.typography.h2,
                        color = Color.Black
                    )
                }
//                val weekSchedules = mutableListOf(
//                    viewModel.schedule.collectAsState().value.data.schedules.Monday?: emptyList(),
//                    viewModel.schedule.collectAsState().value.data.schedules.Tuesday ?: emptyList(),
//                    viewModel.schedule.collectAsState().value.data.schedules.Wednesday?: emptyList(),
//                    viewModel.schedule.collectAsState().value.data.schedules.Thursday?: emptyList(),
//                    viewModel.schedule.collectAsState().value.data.schedules.Friday?: emptyList(),
//                    viewModel.schedule.collectAsState().value.data.schedules.Saturday?: emptyList()
//                )
                Spacer(modifier = Modifier.height(5.dp))
//                    HorizontalPager(
//                        count = weekSchedules.size,
//                        state = pagerState,
//                        verticalAlignment = Alignment.Top,
//                        modifier = Modifier.fillMaxSize()
//
//                    ) { page ->
//                        selectedDayOfCurrentWeek.value = pagerState.currentPage
//                        if (!scheduleState.value.isLoading) {
//                            val lessons: MutableList<Schedule> = emptyList<Schedule>().toMutableList()
//                            weekSchedules[page].forEach{
//                                if (it.weekNumber.contains(selectedWeek.value.toLong())) {
//                                    if (selectedSubGroup.value.toLong() == Constance.ALL_GROUP) {
//                                        lessons.add(it)
//                                    } else if (it.numSubgroup == Constance.ALL_GROUP ||
//                                        it.numSubgroup == selectedSubGroup.value.toLong()
//                                    ) {
//                                        lessons.add(it)
//                                    }
//                                }
//                            }
//                             if (weekSchedules[page].isNotEmpty() && lessons.isNotEmpty()) {
//                            LazyColumn(
//                                verticalArrangement = Arrangement.Top,
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .padding(
//                                        start = 10.dp,
//                                        end = 10.dp)
//                            ) {
//                                items(lessons) {
//                                    ClassItem(lessonInfo = it)
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                }
//                            }
//                               }else{
//                                   Column(
//                                       modifier = Modifier.fillMaxSize(),
//                                   horizontalAlignment = Alignment.CenterHorizontally
//                                       ){
//                                       Image(painter = painterResource(id = R.drawable.owner), contentDescription = "")
//                                       Text(
//                                           text = stringResource(R.string.no_lessons),
//                                           style = MaterialTheme.typography.h2,
//                                           color = Color.Black
//                                       )
//                                   }
//                             }
//                        } else {
//                            Box(modifier = Modifier.fillMaxSize(),
//                                contentAlignment = Alignment.TopCenter) {
//                                CircularProgressIndicator(color = DarkSea)
//                            }
//                        }
//                    }
                }
            }
    }