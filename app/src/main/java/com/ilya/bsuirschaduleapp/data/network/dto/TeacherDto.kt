package com.ilya.bsuirschaduleapp.data.network.dto

import com.ilya.bsuirschaduleapp.domain.models.Teacher

data class TeacherDto(
    val academicDepartment: List<String>,
    val calendarId: String,
    val degree: String,
    val fio: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String?="",
    val urlId: String
)

fun TeacherDto.toTeacher():Teacher{
    var departments = ""
    academicDepartment.forEach{
        departments+= "$it "
    }
    return Teacher(
        fio = fio,
        academicDepartment = departments,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        photoLink = photoLink,
        rank = rank,
        urlId = urlId
    )
}