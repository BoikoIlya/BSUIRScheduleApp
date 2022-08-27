package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.upperui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkGrayForAlert
import com.ilya.bsuirschaduleapp.R

@Composable
fun SelectSubGroupMenu(
    showSelectSubGroupMenu: MutableState<Boolean>,
    selectedSubGroup: State<Int>,
    onClick:(Int)->Unit
){
    val buttonSelected = remember {
        mutableStateOf(selectedSubGroup.value)
    }
    buttonSelected.value = selectedSubGroup.value
    AlertDialog(
        backgroundColor = DarkGrayForAlert,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray),
        onDismissRequest = { },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onClick(buttonSelected.value)
                    showSelectSubGroupMenu.value = false
                }) {
                Text(text = stringResource(id = R.string.ok), color = Color.Black)
            }
        },
        title ={ Text(text = stringResource(R.string.sub_group_choosing)) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        modifier = Modifier.size(50.dp),
                        selected = buttonSelected.value == 0,
                        onClick = { buttonSelected.value = 0 }
                    )
                    Text(
                        text = stringResource(R.string.hole_group),
                        modifier = Modifier.clickable { buttonSelected.value = 0 },
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        modifier = Modifier.size(50.dp),
                        selected = buttonSelected.value == 1,
                        onClick = { buttonSelected.value = 1 })
                    Text(
                        text = stringResource(R.string.first_sub_group),
                        modifier = Modifier.clickable { buttonSelected.value = 1 },
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        modifier = Modifier.size(50.dp),
                        selected = buttonSelected.value == 2,
                        onClick = { buttonSelected.value = 2 })
                    Text(
                        text = stringResource(R.string.second_sub_group),
                        modifier = Modifier.clickable { buttonSelected.value = 2 },
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
            }
        }
    )
}