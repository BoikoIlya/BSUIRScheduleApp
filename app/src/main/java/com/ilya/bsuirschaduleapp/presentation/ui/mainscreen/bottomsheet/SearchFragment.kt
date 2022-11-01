package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.BaseGroupListViewModel
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.BaseTeacherListViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import com.ilya.bsuirschaduleapp.utils.Constance
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchFragment(
    viewModel: BaseTeacherListViewModel = hiltViewModel(),
    showSearch: MutableState<Int>,
    refreshBtnVisibility: MutableState<Boolean>,
    groupViewModel: BaseGroupListViewModel = hiltViewModel(),
) {

    refreshBtnVisibility.value = true

    val textState = remember {
        mutableStateOf("")
    }

    val teacherList = remember {
        mutableStateOf(listOf<TeacherListItemUi>())
    }
    val progress = remember {
        mutableStateOf(false)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(
        key1 = true, block = {
            viewModel.collect(lifecycleOwner) {
                teacherList.value = it
            }
        })
    LaunchedEffect(key1 = true, block = {
        viewModel.collectProgress(lifecycleOwner) {
            progress.value = it
        }
    })





    val groupList = remember {
        mutableStateOf(listOf<GroupListItemUi>())
    }
    val groupsProgress = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(
        key1 = true, block = {
            groupViewModel.collect(lifecycleOwner) {
                groupList.value = it
            }
        })
    LaunchedEffect(key1 = true, block = {
        groupViewModel.collectProgress(lifecycleOwner) {
            groupsProgress.value = it
        }
    })
    //val groupList = viewModel.groupList.collectAsState()
    //val teacherList = viewModel.teacherList.collectAsState()
    //viewModel.obtainActionEvent(ActionEvent.GetTeacherByFioFromDB)
    //viewModel.obtainActionEvent(ActionEvent.GetGroupByNameFromDB)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                unfocusedIndicatorColor = LightSea,
                focusedIndicatorColor = DarkSea,
                backgroundColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            value = textState.value,
            onValueChange = {
                textState.value = it
                if(showSearch.value==Constance.IS_TEACHER)
                viewModel.find(it)
                else groupViewModel.find(it)
                // viewModel.obtainDataEvent(SendDataEvent.SearchName(textState.value))
            },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            },
            placeholder = { Text(text = stringResource(R.string.search), color = Color.Gray) }
        )
        when (showSearch.value) {
            Constance.IS_GROUP -> {
                if (progress.value) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 10.dp)
                    ) {
                        items(groupList.value) {
                            Spacer(modifier = Modifier.height(5.dp))
                           /* ItemTeacher(teacher = it) {
                                viewModel.changeFavorite(it.urlId)
                                showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
                            }*/
                            GroupItem(group = it) {
                                 groupViewModel.changeFavorite(it.name)
                                 showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
                                refreshBtnVisibility.value = false
                            }
                        }
                    }
                }
            }
            Constance.IS_TEACHER -> {
                if (progress.value) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 10.dp)
                    ) {
                        items(teacherList.value) {
                            Spacer(modifier = Modifier.height(5.dp))
                               ItemTeacher(teacher = it) {
                                   viewModel.changeFavorite(it.urlId)
                                   showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
                                   refreshBtnVisibility.value = false
                               }
                        }
                    }
                    /*TeachersLazyColumn(
                    teachers =teacherList,
                    showSearch = showSearch,
                    insert = {
                        viewModel.obtainActionEvent(ActionEvent.InsertSelectedTeacherInDB(it))
                    }
                )*/
                }
            }

        }
    }

}

@Composable
fun ItemTeacher(
    teacher: TeacherListItemUi,
    onSelect: () -> Unit
) {

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
                if(!teacher.isFavorite) onSelect()
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

