package com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation

import androidx.room.PrimaryKey

/**
 * Created by HP on 22.09.2022.
 **/
data class TeacherListItemUi(
    val fio: String,
    val academicDepartment: String,
    val fullFIO:String,
    val photoLink: String,
    val rank: String,
    val urlId: String,
    val isFavorite: Boolean
)