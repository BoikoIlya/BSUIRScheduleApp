package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DaysBar(
    viewModel: MainViewModel = hiltViewModel(),
    selectedWeek: MutableState<Int>,
    selectedDayOfCurrentWeek: MutableState<Int>,
    pagerState: PagerState
){
    val listOfDays = remember {
        mutableStateOf(listOf(UpperUiState(1,1,1)))
    }

    val schedule = viewModel.schedule.collectAsState()
    val c = Calendar.getInstance()
    val dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 2
    val weekNumber = remember {
        mutableStateOf(selectedWeek.value)
    }
    weekNumber.value = selectedWeek.value
    LaunchedEffect(key1 = true, block = {
        selectedDayOfCurrentWeek.value = if (dayOfWeek == 6) 5 else dayOfWeek
        pagerState.scrollToPage(selectedDayOfCurrentWeek.value)
    })

    LazyRow(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        val itemsWeek1 = mutableListOf<UpperUiState>()
        val itemsWeek2 = mutableListOf<UpperUiState>()
        val itemsWeek3 = mutableListOf<UpperUiState>()
        val itemsWeek4 = mutableListOf<UpperUiState>()
        var dayOfWeek = 1

        if (!schedule.value.isLoading) {
            for (i in 0..23) {
                if(i in 0..5) {
                    weekNumber.value = 1
                }
                if(i in 6..11) {
                    weekNumber.value = 2
                }
                if(i in 12..17) {
                    weekNumber.value = 3
                }
                if(i in 18..23) {
                    weekNumber.value = 4
                }
                val item = UpperUiState(
                    index = i,
                    dayOfWeek = dayOfWeek,
                    weekNumber = weekNumber.value,
                )
                when(weekNumber.value){
                    1->itemsWeek1.add(item)
                    2->itemsWeek2.add(item)
                    3->itemsWeek3.add(item)
                    4->itemsWeek4.add(item)
                }

                dayOfWeek++
                if (dayOfWeek == 7) {
                    weekNumber.value++
                    dayOfWeek = 1
                }
            }

            listOfDays.value = when(selectedWeek.value){
                1-> itemsWeek1
                2->itemsWeek2
                3->itemsWeek3
                4->itemsWeek4
                else -> {itemsWeek1}
            }

            items(listOfDays.value.size) {index->
                DayItem(
                    selectedDay = selectedDayOfCurrentWeek,
                    itemIndex = index,
                    pagerState = pagerState
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}