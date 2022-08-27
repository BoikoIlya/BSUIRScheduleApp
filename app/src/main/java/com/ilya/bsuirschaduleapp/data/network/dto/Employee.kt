package com.ilya.bsuirschaduleapp.data.network.dto

data class Employee (
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val degreeAbbrev: String,
    val rank: Any? = null,
    val photoLink: String?="",
    val calendarID: String,
    val academicDepartment: List<String>,
    val id: Long,
    val urlID: String,
    val fio: String,
    val email: Any? = null,
    val department: Any? = null
)