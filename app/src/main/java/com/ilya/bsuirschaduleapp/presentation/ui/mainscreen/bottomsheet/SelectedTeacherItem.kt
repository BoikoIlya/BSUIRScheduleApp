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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.BsuirScheduleAppTheme

import com.ilya.bsuirschaduleapp.presentation.ui.theme.Green
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Typography
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightGreen
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SelectedTeacherItem(
    teacher: TeacherListItemUi,
    onDelete:()->Unit,
    onClick:()->Unit,
    modifier: Modifier
){
    val showAlert = remember {
        mutableStateOf(false)
    }
    if(showAlert.value){
        DeleteItemAlert(
            onClick = { onDelete() },
            showAlert =showAlert )
    }
    Box(
        modifier = modifier
            ,
    ) {
        Column(
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    error = painterResource(id = R.drawable.ic_person),
                    imageModel = teacher.photoLink,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(100.dp),
                    contentScale = ContentScale.Fit)
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = teacher.fullFIO,
                        style = Typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = BsuirScheduleAppTheme.colors.LowerUiTextColor
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = teacher.academicDepartment,
                            fontSize = MaterialTheme.typography.body1.fontSize,
                            color = Color.White,
                            modifier = Modifier
                                .clip(
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .background(BsuirScheduleAppTheme.colors.LowerUiLecturesColorSecondary)
                                .padding(5.dp)
                                .weight(5f)
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
                                .weight(1f)
                        )
                    }
                }
            }


        }
    }
}
