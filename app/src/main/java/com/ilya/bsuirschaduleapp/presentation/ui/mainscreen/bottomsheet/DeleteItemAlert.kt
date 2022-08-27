package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkGrayForAlert

@Composable
fun DeleteItemAlert(
    onClick:()->Unit,
    showAlert: MutableState<Boolean>
){
    if(showAlert.value){
        AlertDialog(
            backgroundColor = DarkGrayForAlert,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Gray),
            onDismissRequest = {},
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        showAlert.value = false
                        onClick()
                    }) {
                    Text(text = stringResource(R.string.delete), color = Color.Black)
                }

            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        showAlert.value = false

                    }) {
                    Text(text = stringResource(R.string.do_not_delete), color = Color.Black)
                }
            },
            title = {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(R.string.are_you_sure_of_deleting))
                }
            },
            text = { Text(text = "                         ")  }
        )
    }
}