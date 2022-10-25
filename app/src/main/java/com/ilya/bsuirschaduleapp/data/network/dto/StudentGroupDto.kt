package com.ilya.bsuirschaduleapp.data.network.dto

import com.ilya.bsuirschaduleapp.domain.models.Group

data class StudentGroupDto (
    val name: String,
    val facultyID: Long,
    val facultyName: String,
    val course: Long,
    val id: Long,
    val calendarID: String
)

fun StudentGroupDto.toGroup(): Group{
    return Group(
        course = course.toInt(),
        facultyName = facultyName,
        name = name,
        specialityName = ""
    )
}