package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


//@Composable
//fun GroupsLazyColumn(
//    groups: State<GroupListState>,
//    showSearch: MutableState<Int>,
//    insert:(SelectedGroup)->Unit
//){
//    if (groups.value.isLoading){
//        Box(modifier = Modifier.fillMaxWidth(),
//         contentAlignment = Alignment.TopCenter
//            ){
//            CircularProgressIndicator()
//        }
//    }else {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White)
//                .padding(horizontal = 10.dp)
//        ) {
//            items(groups.value.data) {
//                Spacer(modifier = Modifier.height(5.dp))
//                /*GroupItem(group = it) {
//                    insert(SelectedGroup(
//                        course = it.course,
//                        name = it.name,
//                        specialityName = it.specialityName))
//                    showSearch.value = 0
//                }*/
//
//            }
//        }
//    }
//}
