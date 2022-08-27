package com.ilya.bsuirschaduleapp.data.network.dto

import com.ilya.bsuirschaduleapp.domain.models.Group


data class GroupDto(
    val calendarId: String,
    val course: Int,
    val facultyId: Int,
    val facultyName: String,
    val id: Int,
    val name: String,
    val specialityDepartmentEducationFormId: Int,
    val specialityName: String
)

fun GroupDto.toGroup(): Group {
    return Group(
        course = course,
        facultyName = facultyName,
        name = name,
        specialityName = specialityName
    )
}