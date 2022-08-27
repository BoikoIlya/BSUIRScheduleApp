package com.ilya.bsuirschaduleapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.utils.Constance

@Entity(tableName = Constance.SCHEDULE_DB_SELECTED_TEACHER_COLUMN)
data class SelectedTeacher(
    val academicDepartment: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String,
    @PrimaryKey(autoGenerate = false)
    val fio: String,
    val urlId: String
)
