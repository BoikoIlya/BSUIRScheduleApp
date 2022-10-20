package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation.BaseFavoriteGroupsViewModel
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.BaseFavoriteTeachersViewModel
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.presentation.FavoritesViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.BaseTeacherListViewModel
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import com.ilya.bsuirschaduleapp.utils.Constance
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    sheetState: BottomSheetState,
    viewModel: BaseFavoriteTeachersViewModel = hiltViewModel(),
    myViewModel: BaseFavoriteGroupsViewModel = hiltViewModel(),
){

    val teacherList = remember {
        mutableStateOf(listOf<TeacherListItemUi>())
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(
        key1 = true, block = {
            viewModel.collect(lifecycleOwner) {
                teacherList.value = it
            }
        })
    LaunchedEffect(
        key1 = true, block = {
            viewModel.collectUpdate(lifecycleOwner){
                if (it) {
                    viewModel.update()
                }
            }
        })



    val groupList = remember {
        mutableStateOf(listOf<GroupListItemUi>())
    }
    LaunchedEffect(
        key1 = true, block = {
            myViewModel.collect(lifecycleOwner) {
                groupList.value = it
            }
        })
    LaunchedEffect(
        key1 = true, block = {
            myViewModel.collectUpdate(lifecycleOwner){
                if (it) {
                    myViewModel.update()
                }
            }
        })


    val showSearch = remember {
        mutableStateOf(0)
    }
    if(sheetState.isCollapsed)
        showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
    val scope = rememberCoroutineScope()
  /*  val selectedGroupList = viewModel.selectedGroupList.collectAsState()
    val selectedTeacherList = viewModel.selectedTeacherList.collectAsState()*/
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
                    .background(DarkSea)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth(0.15f)
                        .fillMaxHeight(0.01f)
                        .background(Color.LightGray),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.users_group),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    showSearch.value = Constance.IS_GROUP
                                }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.add_user),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    showSearch.value = Constance.IS_TEACHER
                                }
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close",
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
            if(showSearch.value!=Constance.NOT_TEACHER_NOT_GROUP){
                SearchFragment(showSearch = showSearch)
            } else {
                /*if(selectedGroupList.value.isLoading || selectedTeacherList.value.isLoading){
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ){
                        CircularProgressIndicator()
                    }*/
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 10.dp)
                ) {
                    items(groupList.value) {
                        Spacer(modifier = Modifier.height(5.dp))
                        SelectedGroupItem(
                            group = it,
                            onDelete = {
                                myViewModel.changeFavorite(it.name)
                            }) {

                        }
                    }
                    items(teacherList.value){
                        Spacer(modifier = Modifier.height(5.dp))
                        SelectedTeacherItem(
                            teacher = it,
                            onDelete = {
                                viewModel.changeFavorite(it.urlId)
                            }) {

                        }
                    }
                  /*items(selectedGroupList.value.data){
                          Spacer(modifier = Modifier.height(5.dp))
                          SelectedGroupItem(
                              selectedGroup = it,
                              {viewModel.obtainActionEvent(ActionEvent.DeleteSelectedGroupFromDB(it))},
                              {
                                  viewModel.obtainDataEvent(SendDataEvent.TeacherOrGroup(Constance.IS_GROUP))

                                  viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                                      it.name,
                                      Constance.SELECTED_GROUP_OR_TEACHER_PATH_FOR_API_KEY))

                                  viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                                      it.name,
                                      Constance.SELECTED_GROUP_OR_TEACHER_NAME_KEY))
                              }
                          )
                  }
                    items(selectedTeacherList.value.data){
                        Spacer(modifier = Modifier.height(5.dp))
                        SelectedTeacherItem(
                            selectedTeacher = it,
                            {viewModel.obtainActionEvent(ActionEvent.DeleteSelectedTeacherFromDB(it))},
                            {
                                viewModel.obtainDataEvent(SendDataEvent.TeacherOrGroup(Constance.IS_TEACHER))

                                viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                                    it.urlId,
                                    Constance.SELECTED_GROUP_OR_TEACHER_PATH_FOR_API_KEY))

                                viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                                    it.fio,
                                    Constance.SELECTED_GROUP_OR_TEACHER_NAME_KEY))

                                viewModel.obtainActionEvent(ActionEvent.SaveDataInDataStore(
                                    Constance.ALL_GROUP.toString(),
                                    Constance.SELECTED_SUBGROUP_KEY
                                ))
                            }
                        )
                    }*/
                }
            }
        }
    }













