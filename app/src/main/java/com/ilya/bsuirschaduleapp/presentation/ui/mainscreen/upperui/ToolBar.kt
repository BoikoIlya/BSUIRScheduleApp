package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun ToolBar(
    sheetState: BottomSheetState,
    onClick:(Int)->Unit,
    selectedSubGroup: State<Int>,
    groupOrTeacherName: String
){
    val showSelectSubGroupMenu = remember{ mutableStateOf(false)}
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu_white),
            contentDescription ="",
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
        Text(
            text = groupOrTeacherName,
            style = MaterialTheme.typography.h3,
        )
     Row() {

         Icon(
             painter = painterResource(id = R.drawable.ic_people),
             contentDescription = "",
             modifier = Modifier
                 .size(40.dp)
                 .clickable {
                     showSelectSubGroupMenu.value = true
                 }
         )
         Text(text = if(selectedSubGroup.value!=0) selectedSubGroup.value.toString() else "")
     }
    }

    if(showSelectSubGroupMenu.value) {
       SelectSubGroupMenu(showSelectSubGroupMenu, selectedSubGroup){
           onClick(it)
       }
    }

}