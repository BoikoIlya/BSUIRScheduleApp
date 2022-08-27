package com.ilya.bsuirschaduleapp.data.network.dto

import java.util.Collections.emptyList

data class Schedule(
    val weekNumber: List<Long>,
    val studentGroups: List<StudentGroup>,
    val numSubgroup: Long,
    val auditories: List<String>?= emptyList(),
    val startLessonTime: String,
    val endLessonTime: String,
    val subject: String,
    val subjectFullName: String,
    val note: String? = null,
    val lessonTypeAbbrev: String,
    val dateLesson: String? = null,
    val employees: List<Employee>?= emptyList(),
    val startLessonDate: String
)