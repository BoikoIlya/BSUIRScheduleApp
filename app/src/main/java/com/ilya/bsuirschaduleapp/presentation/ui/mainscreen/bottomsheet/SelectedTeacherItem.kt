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
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Green
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightGreen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SelectedTeacherItem(
    selectedTeacher: SelectedTeacher,
    onDelete:()->Unit,
    onClick:()->Unit
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
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .background(VeryLightGreen)
            .fillMaxWidth()
            .padding(10.dp),
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
                    imageModel =  selectedTeacher.photoLink,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(100.dp)
                    ,
                    contentScale = ContentScale.Fit)
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = "${selectedTeacher.lastName} ${selectedTeacher.firstName} ${selectedTeacher.middleName}",
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedTeacher.academicDepartment,
                            fontSize = MaterialTheme.typography.body1.fontSize,
                            color = Color.White,
                            modifier = Modifier
                                .clip(
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .background(Green)
                                .padding(5.dp)
                                .weight(5f)

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
                                .weight(1f)
                        )
                    }
                }
            }


        }
    }
}
