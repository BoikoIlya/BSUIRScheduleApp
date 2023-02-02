package com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by HP on 24.10.2022.
 **/
@Entity(tableName = "groups_list")
data class GroupCache(
    val course: Int,
    val facultyName: String,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val specialityName: String,
    val date: Long
)
