package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by HP on 24.10.2022.
 **/
@Entity(tableName = "teacher_list_item_ui")
data class TeacherCache(
    val fio: String,
    val academicDepartment: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String,
    @PrimaryKey(autoGenerate = false)
    val urlId: String,
    val date: Long
)