package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Purple
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightPurple
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi

@Composable
fun SelectedGroupItem(
    group: GroupListItemUi,
    onDelete:()->Unit,
    onClick:()->Unit,
    modifier: Modifier
){

    Box(
        modifier = modifier

    ) {
        val showAlert = remember {
            mutableStateOf(false)
        }
        if(showAlert.value)
            DeleteItemAlert(onClick = { onDelete() }, showAlert =showAlert )
        Column(
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Text(
                text = group.specialityName,
                style = Typography.body1,
                color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorSecondary
            )
            Text(
                text = group.name,
                style = Typography.h2,
                fontWeight = FontWeight.Bold,
                color = BsuirScheduleAppTheme.colors.LowerUiLessonsTextColorPrimary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = group.course,
                    style = Typography.h4,
                    color = Color.White,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(BsuirScheduleAppTheme.colors.LowerUiPracticalLessonsColorSecondary)
                        .padding(5.dp)

                )
                Icon(
                    painter = painterResource(
                        R.drawable.ic_delete),
                    contentDescription = "",
                    tint = BsuirScheduleAppTheme.colors.LowerUiTextColor,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            showAlert.value = true
                        }
                )
            }
        }
    }
}