package com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.lowerui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilya.bsuirschaduleapp.R
import com.ilya.bsuirschaduleapp.presentation.ui.theme.DarkGrayForAlert

@Composable
fun ConnectionFailed(
    showAlert: State<Boolean>,
    check:()->Unit
){
    if (showAlert.value) {
        AlertDialog(
            backgroundColor = DarkGrayForAlert,
            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
            onDismissRequest = {},
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    shape = RoundedCornerShape(20.dp),
                    onClick = {
                        check()
                    }) {
                    Text(text = stringResource(R.string.repeat), color = Color.Black)
                }
            },
            title = { Text(text = stringResource(R.string.please_check_connection), color = Color.White) },
        )
    }
}