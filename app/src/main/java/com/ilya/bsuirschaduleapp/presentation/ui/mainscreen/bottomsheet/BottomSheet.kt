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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.models.ActionEvent
import com.ilya.bsuirschaduleapp.presentation.models.SendDataEvent
import com.ilya.bsuirschaduleapp.presentation.ui.theme.*
import com.ilya.bsuirschaduleapp.presentation.viewmodels.MainViewModel
import com.ilya.bsuirschaduleapp.utils.Constance
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    sheetState: BottomSheetState,
    viewModel: MainViewModel = hiltViewModel(),
){
    val showSearch = remember {
        mutableStateOf(0)
    }
    if(sheetState.isCollapsed)
        showSearch.value = Constance.NOT_TEACHER_NOT_GROUP
    val scope = rememberCoroutineScope()
    val selectedGroupList = viewModel.selectedGroupList.collectAsState()
    val selectedTeacherList = viewModel.selectedTeacherList.collectAsState()
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
                SearchFragment(viewModel, showSearch)
            } else {
                if(selectedGroupList.value.isLoading || selectedTeacherList.value.isLoading){
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ){
                        CircularProgressIndicator()
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(horizontal = 10.dp)
                ) {
                  items(selectedGroupList.value.data){
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
                            }
                        )
                    }
                }
            }
        }
    }
}












