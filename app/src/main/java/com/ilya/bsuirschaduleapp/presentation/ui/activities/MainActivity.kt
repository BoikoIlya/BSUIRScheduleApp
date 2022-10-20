package com.ilya.bsuirschaduleapp.presentation.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.ilya.bsuirschaduleapp.presentation.ui.mainscreen.*
import com.ilya.bsuirschaduleapp.reafactor.core.GlobalErrorCommunication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   @Inject
   lateinit var globalErrorCommunication: GlobalErrorCommunication.Mutable
   //private val viewModel: by viewModels()
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lif = LocalLifecycleOwner.current
            MainScreen()
           /* LaunchedEffect(key1 = true, block = {
                globalErrorCommunication.collect(this@MainActivity){
                    if(it.isNotEmpty())
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            })
            val viewModel: TeacherListViewModel = hiltViewModel()
            BSUIRSchaduleAppTheme {
                val teacherList = remember {
                    mutableStateOf(listOf<TeacherListItemUi>())
                }
                val progress = remember {
                    mutableStateOf(false)
                }
                val textState = remember {
                    mutableStateOf("")
                }
                LaunchedEffect(
                    key1 = true, block = {
                        viewModel.collect(this@MainActivity) {
                            Log.d("mytag", "$it")
                            teacherList.value = it
                        }
                    })
                LaunchedEffect(key1 = true, block = {
                    viewModel.collectProgress(this@MainActivity) {
                        progress.value = it
                    }
                })
                Column(modifier = Modifier.fillMaxSize()) {
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                            unfocusedIndicatorColor = LightSea,
                            focusedIndicatorColor = DarkSea
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        value = textState.value,
                        onValueChange = {

                            textState.value = it
                            viewModel.findTeacher(textState.value)
                        },
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "",
                                tint = Color.DarkGray
                            )
                        },
                        placeholder = {
                            Text(text = stringResource(R.string.search),
                                color = Color.Gray)
                        }
                    )
                    if (progress.value) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(color = Color.Black)
                        }
                    } else {
                        LazyColumn {
                            items(teacherList.value) {
                                Text(text = it.fio, color = Color.Black)
                            }
                        }
                    }
                }
            }*/
        }
    }
}



