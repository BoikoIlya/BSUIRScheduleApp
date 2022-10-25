package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Purple
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightPurple
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi

@Composable
fun GroupItem(
    group: GroupListItemUi,
    onSelect:()->Unit
){

    Box(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .background(VeryLightPurple)
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier.clickable {
                onSelect()
            }
        ) {
            Text(
                text = group.specialityName,
                style = Typography.body1,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = group.name,
                    style = Typography.h2,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = group.course,
                    style = Typography.h4,
                    color = Color.White,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Purple)
                        .padding(5.dp)

                )
            }
        }
    }
}
