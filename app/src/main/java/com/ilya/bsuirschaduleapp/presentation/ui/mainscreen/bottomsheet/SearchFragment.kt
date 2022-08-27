package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.models.ActionEvent
import com.ilya.bsuirschaduleapp.presentation.models.SendDataEvent
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkSea
import com.ilya.bsuirschaduleapp.presentation.ui.theme.LightSea
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.utils.Constance

@Composable
fun SearchFragment(
    viewModel: MainViewModel,
    showSearch: MutableState<Int>
){
    val textState = remember {
        mutableStateOf("")
    }
    val groupList = viewModel.groupList.collectAsState()
    val teacherList = viewModel.teacherList.collectAsState()
    viewModel.obtainActionEvent(ActionEvent.GetTeacherByFioFromDB)
    viewModel.obtainActionEvent(ActionEvent.GetGroupByNameFromDB)
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                unfocusedIndicatorColor = LightSea,
                focusedIndicatorColor = DarkSea
            ),
            modifier = Modifier.fillMaxWidth(),
            value =textState.value,
            onValueChange ={

                textState.value = it
                viewModel.obtainDataEvent(SendDataEvent.SearchName(textState.value))
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            },
            placeholder = { Text(text = stringResource(R.string.search), color = Color.Gray) }
        )
        when(showSearch.value){
            Constance.IS_GROUP->{
                GroupsLazyColumn(
                    groups =groupList,
                    showSearch = showSearch) {
                    viewModel.obtainActionEvent(ActionEvent.InsertSelectedGroupInDB(it))
                }
            }
            Constance.IS_TEACHER->{
                TeachersLazyColumn(
                    teachers =teacherList,
                    showSearch = showSearch,
                    insert = {
                        viewModel.obtainActionEvent(ActionEvent.InsertSelectedTeacherInDB(it))
                    }
                )
            }
        }

    }
}