package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun DayItem(
    itemIndex: Int,
    selectedDay:MutableState<Int>,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(70.dp)
                .clip(shape = RoundedCornerShape(20.dp))

                .clickable {

                }
                .background(
                    color = if (selectedDay.value == itemIndex) BsuirScheduleAppTheme.colors.UpperUiSelection
                    else BsuirScheduleAppTheme.colors.UpperUiSecondary
                )
                .selectable(
                    selected = selectedDay.value == itemIndex,
                    onClick = {
                        selectedDay.value = itemIndex
                        scope.launch {
                            if(pagerState.pageCount==6)
                            pagerState.scrollToPage(itemIndex)
                        }
                    }
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when(itemIndex){
                    0-> stringResource(R.string.monday)
                    1-> stringResource(R.string.tuesday)
                    2-> stringResource(R.string.wednesday)
                    3-> stringResource(R.string.thursday)
                    4-> stringResource(R.string.friday)
                    5-> stringResource(R.string.saturday)
                        else->""
                                                 },
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.body1.fontSize,
                    color = if(selectedDay.value==itemIndex)  BsuirScheduleAppTheme.colors.UpperUiPrimary
                    else BsuirScheduleAppTheme.colors.UpperUiSelection
                    )
            }
        }

}