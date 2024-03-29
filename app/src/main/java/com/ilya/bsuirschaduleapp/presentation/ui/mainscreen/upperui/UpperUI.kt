package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme

import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography

import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun UpperUI(
    changeSubGroup:(Int)->Unit,
    sheetState: BottomSheetState,
   // viewModel: MainViewModel = hiltViewModel(),
    selectedDayOfCurrentWeek: MutableState<Int>,
    selectedWeek: MutableState<Int>,
    pagerState: PagerState,
    scheduleState: MutableState<List<List<ScheduleDomain.Schedule>>>,
    selectedGroupOrTeacherName: MutableState<String>,
    selectedSubGroup: MutableState<Int>,
    progressLoading: MutableState<Boolean>,
    currentWeek: MutableState<String>
){
    //val currentWeek  = remember{ mutableStateOf(2)} //viewModel.currWeek.collectAsState()
    //val selectedSubGroup = remember { mutableStateOf(0)}
   // val selectedGroupOrTeacherName = viewModel.selectedGroupOrTeacherName.collectAsState()




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BsuirScheduleAppTheme.colors.UpperUiPrimary)

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ToolBar(
            sheetState,
            { selectedSubGroup->
//            viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
//                //selectedSubGroup.toString(),
//                Constance.SELECTED_SUBGROUP_KEY
                changeSubGroup(selectedSubGroup)

        },
            selectedSubGroup,
            selectedGroupOrTeacherName.value
            )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.curr_week) +currentWeek.value,
                style = Typography.h2,
                color = BsuirScheduleAppTheme.colors.StaticTextColor,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                )
            )

            Row(verticalAlignment = Alignment.CenterVertically, ) {
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(40.dp)
                        .clip(
                            RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
                        )
                        .clickable {
                            selectedWeek.value = 1
                        }
                        .background(color = if (selectedWeek.value == 1) BsuirScheduleAppTheme.colors.UpperUiSelection
                        else BsuirScheduleAppTheme.colors.UpperUiSecondary),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.first_week),
                        textAlign = TextAlign.Center,
                    style = Typography.h4,
                    color = if(selectedWeek.value==1)  BsuirScheduleAppTheme.colors.UpperUiPrimary
                    else BsuirScheduleAppTheme.colors.StaticTextColor)

                }
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedWeek.value = 2
                        }
                        .width(55.dp)
                        .height(40.dp)
                        .background(color = if (selectedWeek.value == 2) BsuirScheduleAppTheme.colors.UpperUiSelection
                        else BsuirScheduleAppTheme.colors.UpperUiSecondary),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.second_week),
                        textAlign = TextAlign.Center,
                        style = Typography.h4,
                        color = if(selectedWeek.value==2)  BsuirScheduleAppTheme.colors.UpperUiPrimary
                        else BsuirScheduleAppTheme.colors.StaticTextColor)
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedWeek.value = 3
                        }
                        .width(55.dp)
                        .height(40.dp)
                        .background(color = if (selectedWeek.value == 3) BsuirScheduleAppTheme.colors.UpperUiSelection
                        else BsuirScheduleAppTheme.colors.UpperUiSecondary),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.third_week),
                        textAlign = TextAlign.Center,
                        style = Typography.h4,
                        color = if(selectedWeek.value==3)  BsuirScheduleAppTheme.colors.UpperUiPrimary  else
                            BsuirScheduleAppTheme.colors.StaticTextColor)
                }
                Box(
                    modifier = Modifier
                        .width(55.dp)
                        .height(40.dp)
                        .clip(
                            RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
                        .clickable {
                            selectedWeek.value = 4
                        }
                        .background(color = if (selectedWeek.value == 4) BsuirScheduleAppTheme.colors.UpperUiSelection
                        else BsuirScheduleAppTheme.colors.UpperUiSecondary),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.fourth_week),
                        textAlign = TextAlign.Center,
                        style = Typography.h4,
                        color = if(selectedWeek.value==4)  BsuirScheduleAppTheme.colors.UpperUiPrimary
                        else BsuirScheduleAppTheme.colors.StaticTextColor)
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        if(!progressLoading.value) {
            DaysBar(
                selectedWeek = selectedWeek,
                selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
                pagerState = pagerState,
            )
        }
    }
}
