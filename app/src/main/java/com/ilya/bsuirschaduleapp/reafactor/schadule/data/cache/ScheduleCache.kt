package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilya.bsuirschaduleapp.data.network.dto.Employee
import com.ilya.bsuirschaduleapp.data.network.dto.StudentGroup
import java.util.*

/**
 * Created by HP on 25.10.2022.
 **/
@Entity(tableName = "schedules")
data class ScheduleCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dayOfWeek: Int,
    val weekNumber: List<Long>,
    val studentGroups: List<StudentGroup>,
    val numSubgroup: Long,
    val auditories: List<String>?= Collections.emptyList(),
    val startLessonTime: String,
    val endLessonTime: String,
    val subject: String,
    val subjectFullName: String,
    val note: String? = null,
    val lessonTypeAbbrev: String,
    val employees: List<Employee>?= Collections.emptyList(),
    val date: Long
)
