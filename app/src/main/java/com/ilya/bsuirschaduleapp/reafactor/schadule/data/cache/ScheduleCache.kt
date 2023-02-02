package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

/**
 * Created by HP on 25.10.2022.
 **/
@Entity(tableName = "schedules")
data class ScheduleCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dayOfWeek: Int,
    val weekNumber: String,
    val studentGroups: String,
    val numSubgroup: Long,
    val auditories: String,
    val startLessonTime: String,
    val endLessonTime: String,
    val subject: String,
    val subjectFullName: String,
    val note: String,
    val lessonTypeAbbrev: String,
    val employees: String,
    val date: Long,
    val employeePhotoLink:String,
    val employeeFio: String
)
