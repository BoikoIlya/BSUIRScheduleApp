package com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation

import androidx.room.PrimaryKey

/**
 * Created by HP on 08.10.2022.
 **/
data class GroupListItemUi(
    val course: String,
    val facultyName: String,
    val name: String,
    val specialityName: String,
    val isFavorite: Boolean
)
