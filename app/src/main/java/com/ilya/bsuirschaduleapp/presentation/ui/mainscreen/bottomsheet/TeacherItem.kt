package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import com.ilya.bsuirschaduleapp.presentation.ui.theme.Green
import com.ilya.bsuirschaduleapp.presentation.ui.theme.VeryLightGreen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TeacherItem(
    teacher: Teacher,
    onSelect:()->Unit
){

    Box(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .background(VeryLightGreen)
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        val selected = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.clickable {
                onSelect()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    imageModel =  teacher.photoLink,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(100.dp)
                    ,
                    contentScale = ContentScale.Fit)
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = "${teacher.lastName} ${teacher.firstName} ${teacher.middleName}",
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = teacher.academicDepartment,
                        fontSize = MaterialTheme.typography.body1.fontSize,
                        color = Color.White,
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(Green)
                            .padding(5.dp)

                    )
                }
            }


        }
    }
}