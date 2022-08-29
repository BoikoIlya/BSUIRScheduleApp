package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet.BottomSheetContent
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.ConnectionFailed
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui.LowerUI
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui.UpperUI
import com.ilya.bsuirschaduleapp.presentation.models.ActionEvent
import com.ilya.bsuirschaduleapp.presentation.models.SendDataEvent
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkSea
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.utils.Constance

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
){
    val pagerState = rememberPagerState()
    val selectedDay = remember {
        mutableStateOf(UpperUiState(0,1 , 1))
    }
    val selectedDayOfCurrentWeek = remember {
        mutableStateOf(0)
    }
    val selectedWeek = remember {
        mutableStateOf(1)
    }
    val showNoConnectionAlert = viewModel.noConnection.collectAsState()
    val teacherOrGroup = viewModel.teacherOrGroup.collectAsState()

    if(showNoConnectionAlert.value) {
        ConnectionFailed(showAlert = showNoConnectionAlert) {
                viewModel.obtainDataEvent(SendDataEvent.ConnectionState(false))
            if(teacherOrGroup.value==Constance.IS_GROUP) {
                viewModel.obtainActionEvent(ActionEvent.GetScheduleByGroupNumber)
            }else if(teacherOrGroup.value==Constance.IS_TEACHER)
                viewModel.obtainActionEvent(ActionEvent.GetScheduleByTeacherUrlId)
        }
    }
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
                sheetContent = { BottomSheetContent(sheetState) },
                sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(DarkSea)
                        .fillMaxSize(),
                ) {
                        UpperUI(
                            sheetState,
                            selectedDay = selectedDay,
                            selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
                           selectedWeek=  selectedWeek,
                           pagerState =  pagerState
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        LowerUI(
                            selectedDayItem = selectedDay,
                            viewModel = viewModel,
                            selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
                            selectedWeek=  selectedWeek,
                            pagerState = pagerState
                        )

                }
            }
        }
    }