package com.ilya.bsuirschaduleapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.utils.Constance

@Entity(tableName = Constance.SCHEDULE_DB_GROUP_COLUMN)
data class Group(
    val course: Int,
    val facultyName: String?="",
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val specialityName: String
)
