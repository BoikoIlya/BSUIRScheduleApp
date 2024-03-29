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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.ClassItem
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Sea
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LowerUI(
    scheduleState: MutableState<List<List<ScheduleDomain.Schedule>>>,
    selectedDayOfCurrentWeek: MutableState<Int>,
    selectedWeek: MutableState<Int>,
    pagerState: PagerState,
    progressLoading: MutableState<Boolean>
) {


        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 45.dp,
                        topEnd = 44.dp
                    )
                )
                .fillMaxSize()
                .background(BsuirScheduleAppTheme.colors.AppPrimary)
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
                        style = Typography.h1,
                        color = BsuirScheduleAppTheme.colors.LowerUiTextColor
                    )
                }


                Spacer(modifier = Modifier.height(5.dp))
                    HorizontalPager(
                        count = scheduleState.value.size,
                        state = pagerState,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxSize()

                    ) { page ->
                        selectedDayOfCurrentWeek.value = pagerState.currentPage
                        if (!progressLoading.value) {
                            val lessons: MutableList<ScheduleDomain.Schedule> = emptyList<ScheduleDomain.Schedule>().toMutableList()
                            scheduleState.value[page].forEach{
                                if (it.weekNumber.contains(selectedWeek.value.toString())) {
                                        lessons.add(it)
                                }
                            }
                             if (scheduleState.value[page].isNotEmpty() && lessons.isNotEmpty()) {
                            LazyColumn(
                                verticalArrangement = Arrangement.Top,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp)
                            ) {
                                items(lessons) {
                                    ClassItem(lessonInfo = it)
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                            }
                               }else{
                                   Column(
                                       modifier = Modifier.fillMaxSize(),
                                   horizontalAlignment = Alignment.CenterHorizontally
                                       ){
                                       Image(
                                           painter = painterResource(id = R.drawable.owner),
                                           contentDescription = "",
                                           colorFilter = ColorFilter.tint(color = BsuirScheduleAppTheme.colors.LowerUiTextColor)
                                       )
                                       Text(
                                           text = stringResource(R.string.no_lessons),
                                           style = Typography.h2,
                                           color = BsuirScheduleAppTheme.colors.LowerUiTextColor
                                       )
                                   }
                             }
                        } else {
                            Box(modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.TopCenter) {
                                CircularProgressIndicator(color = Sea)
                            }
                        }
                    }
                }
            }
    }