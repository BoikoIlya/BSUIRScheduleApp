package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet.BottomSheetContent
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.LowerUI
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui.UpperUI
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Sea
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleViewModel

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun MainScreen(
   // viewModel: MainViewModel = hiltViewModel(),
    scheduleViewModel: ScheduleViewModel.Base = hiltViewModel(),
    onChangeTheme:()->Unit
){

    val scheduleState = remember{
        mutableStateOf(listOf(
            emptyList<ScheduleDomain.Schedule>(),
            emptyList<ScheduleDomain.Schedule>(),
            emptyList<ScheduleDomain.Schedule>(),
            emptyList<ScheduleDomain.Schedule>(),
            emptyList<ScheduleDomain.Schedule>(),
            emptyList<ScheduleDomain.Schedule>())
        )
    }
    val progressLoading = remember { mutableStateOf(true) }
    val selectedGroupOrTeacherName = remember { mutableStateOf("") }
    val subGroupState = remember { mutableStateOf(0) }
    val currentWeekState = remember {mutableStateOf("1")}
    val selectedWeek = remember { mutableStateOf(1) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current



    LaunchedEffect(key1 = true, block = {
        scheduleViewModel.collectCurrWeek(lifecycleOwner){
         currentWeekState.value = it
            if(it.isNotEmpty())
            selectedWeek.value = it.toInt()
        }
    })

    LaunchedEffect(key1 = true, block = {
        scheduleViewModel.globalErrorCommunication.collect(lifecycleOwner){
         Toast.makeText(context, it, Toast.LENGTH_SHORT ).show()
        }
    })

    LaunchedEffect(key1 = true, block = {
        scheduleViewModel.collectProgress(lifecycleOwner){progress->
            progressLoading.value= progress
            //selectedSubGroup.value = data.
        }
    })

    LaunchedEffect(key1 = true, block = {
        scheduleViewModel.collect(lifecycleOwner){data->
            scheduleState.value = data.schedules
            selectedGroupOrTeacherName.value = data.name
        }
    })

    LaunchedEffect(key1 = true, block = {
        scheduleViewModel.collectSubGroup(lifecycleOwner){subGroup->
            subGroupState.value = subGroup
        }
    })

    val pagerState = rememberPagerState()
    val selectedDayOfCurrentWeek = remember { mutableStateOf(0) }

//    val showNoConnectionAlert = viewModel.noConnection.collectAsState()
//    val teacherOrGroup = viewModel.teacherOrGroup.collectAsState()

   /* if(showNoConnectionAlert.value) {
        ConnectionFailed(showAlert = showNoConnectionAlert) {
                viewModel.obtainDataEvent(SendDataEvent.ConnectionState(false))
            if(teacherOrGroup.value==Constance.IS_GROUP) {
                viewModel.obtainActionEvent(ActionEvent.GetScheduleByGroupNumber)
            }else if(teacherOrGroup.value==Constance.IS_TEACHER)
                viewModel.obtainActionEvent(ActionEvent.GetScheduleByTeacherUrlId)
        }
    }*/
        val sheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = sheetState
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            BottomSheetScaffold(
                sheetPeekHeight = 0.dp,
                scaffoldState = scaffoldState,
                sheetContent = {
                    BottomSheetContent(
                        sheetState,
                        onSelect = { id -> scheduleViewModel.changeSelectedSchedule(id) },
                        onChangeTheme = { onChangeTheme() }
                    ) },
                sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(BsuirScheduleAppTheme.colors.UpperUiPrimary)
                        .fillMaxSize(),
                ) {
                        UpperUI(
                            {subGroup:Int-> scheduleViewModel.changeSubGroup(subGroup)},
                            sheetState,
                            selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
                           selectedWeek=  selectedWeek,
                           pagerState =  pagerState,
                            scheduleState = scheduleState,
                            selectedGroupOrTeacherName =selectedGroupOrTeacherName,
                            selectedSubGroup = subGroupState,
                            progressLoading = progressLoading,
                            currentWeek = currentWeekState
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        LowerUI(
                            scheduleState = scheduleState,
                            selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
                            selectedWeek=  selectedWeek,
                            pagerState = pagerState,
                            progressLoading = progressLoading
                        )

                }
            }
        }
    }