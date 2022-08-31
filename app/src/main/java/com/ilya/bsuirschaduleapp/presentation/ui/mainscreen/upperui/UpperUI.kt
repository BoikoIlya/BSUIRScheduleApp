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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.models.ActionEvent
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkSea
import com.ilya.bsuirschaduleapp.presentation.ui.theme.LightSea
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.utils.Constance

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun UpperUI(
    sheetState: BottomSheetState,
    viewModel: MainViewModel = hiltViewModel(),
    selectedDayOfCurrentWeek: MutableState<Int>,
    selectedWeek: MutableState<Int>,
){
    val currentWeek  = viewModel.currWeek.collectAsState()
    val scheduleState = viewModel.schedule.collectAsState()
    val selectedSubGroup = viewModel.selectedSubGroup.collectAsState()
    val selectedGroupOrTeacherName = viewModel.selectedGroupOrTeacherName.collectAsState()

    LaunchedEffect(key1 = scheduleState.value.isLoading, block = {
        selectedWeek.value = currentWeek.value
    })


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkSea)

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ToolBar(
            sheetState,
            { selectedSubGroup->
            viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                selectedSubGroup.toString(),
                Constance.SELECTED_SUBGROUP_KEY
            ))
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
                style = MaterialTheme.typography.h2,
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
                        .background(color = if (selectedWeek.value == 1) Color.White else LightSea),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.first_week),
                        textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = if(selectedWeek.value==1)  DarkSea else Color.White)

                }
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedWeek.value = 2
                        }
                        .width(55.dp)
                        .height(40.dp)
                        .background(color = if (selectedWeek.value == 2) Color.White else LightSea),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.second_week),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        color = if(selectedWeek.value==2)  DarkSea else Color.White)
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            selectedWeek.value = 3
                        }
                        .width(55.dp)
                        .height(40.dp)
                        .background(color = if (selectedWeek.value == 3) Color.White else LightSea),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.third_week),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        color = if(selectedWeek.value==3)  DarkSea else Color.White)
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
                        .background(color = if (selectedWeek.value == 4) Color.White else LightSea),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = stringResource(R.string.fourth_week),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        color = if(selectedWeek.value==4)  DarkSea else Color.White)
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        DaysBar(
            selectedWeek = selectedWeek,
            selectedDayOfCurrentWeek = selectedDayOfCurrentWeek,
        )
    }
}
