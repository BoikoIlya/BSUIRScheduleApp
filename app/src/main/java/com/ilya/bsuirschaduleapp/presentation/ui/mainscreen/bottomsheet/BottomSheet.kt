package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.BaseGroupListViewModel
import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.BaseFavoriteGroupsViewModel
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.BaseFavoriteTeachersViewModel
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.BaseTeacherListViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import com.ilya.bsuirschaduleapp.utils.Constance
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    sheetState: BottomSheetState,
    teachersViewModel: BaseFavoriteTeachersViewModel = hiltViewModel(),
    groursViewModel: BaseFavoriteGroupsViewModel = hiltViewModel(),
    teacherListViewModel: BaseTeacherListViewModel = hiltViewModel(),
    groupListViewModel: BaseGroupListViewModel = hiltViewModel(),
    onSelect:(String)->Unit,
    onChangeTheme:()->Unit,
    exams: MutableState<List<ScheduleDomain.Schedule>>
){

    val teacherList = remember {
        mutableStateOf(listOf<TeacherListItemUi>())
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(
        key1 = true, block = {
            teachersViewModel.collect(lifecycleOwner) {
                teacherList.value = it
            }
        })
    LaunchedEffect(
        key1 = true, block = {
            teachersViewModel.collectUpdate(lifecycleOwner){
                if (it) {
                    teachersViewModel.fetchData()
                }
            }
        })



    val groupList = remember {
        mutableStateOf(listOf<GroupListItemUi>())
    }
    LaunchedEffect(
        key1 = true, block = {
            groursViewModel.collect(lifecycleOwner) {
                groupList.value = it
            }
        })
    LaunchedEffect(
        key1 = true, block = {
            groursViewModel.collectUpdate(lifecycleOwner){
                if (it) {
                    groursViewModel.fetchData()
                }
            }
        })

    val refreshBtnVisibility = remember {
       mutableStateOf(false)
    }

    val showSearch = remember {
        mutableStateOf(0)
    }

    val showExams = remember {
        mutableStateOf(false)
    }

    val backArrowVisibility = remember {
        mutableStateOf(false)
    }

    if(sheetState.isCollapsed){
        showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
        refreshBtnVisibility.value = false
        showExams.value = false
        backArrowVisibility.value = false
    }

    val scope = rememberCoroutineScope()



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.66f)
    ) {
        Column (Modifier
            .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BsuirScheduleAppTheme.colors.UpperUiPrimary)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth(0.15f)
                        .fillMaxHeight(0.01f)
                        .background(BsuirScheduleAppTheme.colors.AppPrimary),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        AnimatedVisibility(visible = backArrowVisibility.value) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_back),
                                contentDescription = "",
                                tint = BsuirScheduleAppTheme.colors.IconTint,
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
                                        refreshBtnVisibility.value = false
                                        showExams.value = false
                                        backArrowVisibility.value = false
                                    }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.users_group),
                            contentDescription = "",
                            tint = BsuirScheduleAppTheme.colors.IconTint,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    showSearch.value = Constance.IS_GROUP
                                    backArrowVisibility.value = true
                                }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.add_user),
                            contentDescription = "",
                            tint =BsuirScheduleAppTheme.colors.IconTint,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    showSearch.value = Constance.IS_TEACHER
                                    backArrowVisibility.value = true
                                }
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AnimatedVisibility(visible = !refreshBtnVisibility.value) {
                            Text(
                                text = "ЭК",
                                style = Typography.h2,
                                color = BsuirScheduleAppTheme.colors.IconTint,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .clickable {
                                    showExams.value = true
                                        backArrowVisibility.value = true
                                    }

                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_theme),
                            contentDescription = "",
                            tint = BsuirScheduleAppTheme.colors.IconTint,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    onChangeTheme()
                                }
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        AnimatedVisibility(visible = refreshBtnVisibility.value) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_refresh),
                                contentDescription = "refresh",
                                tint = BsuirScheduleAppTheme.colors.IconTint,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        teacherListViewModel.refresh()
                                        groupListViewModel.refresh()
                                    }
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "close",
                            tint = BsuirScheduleAppTheme.colors.IconTint,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else sheetState.collapse()
                                    }
                                }
                        )
                    }
                }
            }
            AnimatedVisibility(visible = showSearch.value!=Constance.NOT_TEACHER_NOT_GROUP
            ) {
                SearchFragment(showSearch = showSearch,refreshBtnVisibility = refreshBtnVisibility){
                    backArrowVisibility.value = false
                }
            }

            AnimatedVisibility(visible = showExams.value) {
                ShowExams(exams = exams)
            }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BsuirScheduleAppTheme.colors.AppPrimary)
                        .padding(horizontal = 10.dp)
                ) {
                    items(groupList.value) {
                        Spacer(modifier = Modifier.height(5.dp))
                        SelectedGroupItem(
                            group = it,
                            onDelete = {
                                groursViewModel.changeFavorite(it.name)
                            }, onClick = {
                                onSelect(it.name)
                            },
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(20.dp)
                                )
                            .background(BsuirScheduleAppTheme.colors.LowerUiPracticalLessonsColorPrimary)
                            .fillMaxWidth()
                            .padding(10.dp)
                            .animateItemPlacement(tween(600))
                            )
                    }
                    items(teacherList.value){
                        Spacer(modifier = Modifier.height(5.dp))
                        SelectedTeacherItem(
                            teacher = it,
                            onDelete = {
                                teachersViewModel.changeFavorite(it.urlId)
                            }, onClick = {
                                onSelect(it.urlId)
                            },
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(20.dp)
                            )
                            .background(BsuirScheduleAppTheme.colors.LowerUiLecturesColorPrimary)
                            .fillMaxWidth()
                            .padding(10.dp)
                            .animateItemPlacement(tween(300))
                            )
                    }
                }
            }
        }
    }



@Composable
fun DoRefresh(execute:()->Unit){
    Icon(
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = "refresh",
        modifier = Modifier
            .size(40.dp)
            .clickable {
                execute()
            }
    )
}









