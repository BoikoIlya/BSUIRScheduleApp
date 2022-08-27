package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Purple
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightPurple

@Composable
fun SelectedGroupItem(
    selectedGroup: SelectedGroup,
    onDelete:()->Unit,
    onClick:()->Unit
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
                text = selectedGroup.specialityName,
                fontSize = MaterialTheme.typography.body1.fontSize,
                color = Color.Gray
            )
            Text(
                text = selectedGroup.name,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.course) +selectedGroup.course,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Purple)
                        .padding(5.dp)

                )
                Icon(
                    painter = painterResource(
                        R.drawable.ic_delete),
                    contentDescription = "",
                    tint = Color.Black,
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