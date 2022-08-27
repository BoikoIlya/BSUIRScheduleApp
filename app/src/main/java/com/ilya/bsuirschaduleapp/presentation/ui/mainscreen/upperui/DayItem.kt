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
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.models.UpperUiState
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*


@Composable
fun DayItem(
    itemSelection: MutableState<Int>,
    data: UpperUiState,
    selectedDay:MutableState<UpperUiState>
) {

       if (itemSelection.value == data.dayOfWeek){
           selectedDay.value = data
       }

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(70.dp)
                .clip(shape = RoundedCornerShape(20.dp))

                .clickable {

                }
                .background(
                    color = if(itemSelection.value==data.dayOfWeek) Color.White else LightSea
                )
                .selectable(
                    selected = itemSelection.value == data.dayOfWeek,
                    onClick = {
                        itemSelection.value =
                            when {
                                itemSelection.value!=data.dayOfWeek -> data.dayOfWeek
                                itemSelection.value == data.dayOfWeek -> data.dayOfWeek
                                else -> -1
                            }
                    }
                )
                .padding(5.dp)

                ,
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when(data.dayOfWeek){
                    1-> stringResource(R.string.monday)
                    2-> stringResource(R.string.tuesday)
                    3-> stringResource(R.string.wednesday)
                    4-> stringResource(R.string.thursday)
                    5-> stringResource(R.string.friday)
                    6-> stringResource(R.string.saturday)
                        else->""
                                                 },
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.body1.fontSize,
                    color = if(itemSelection.value==data.dayOfWeek)  DarkSea else Color.White
                    )
            }
        }

}