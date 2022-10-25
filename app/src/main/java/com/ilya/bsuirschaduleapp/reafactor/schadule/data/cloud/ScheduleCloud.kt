package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud

import com.google.gson.annotations.SerializedName
import com.ilya.bsuirschaduleapp.data.network.dto.Employee

import com.ilya.bsuirschaduleapp.data.network.dto.StudentGroup
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import java.util.*

/**
 * Created by HP on 24.10.2022.
 **/
interface ScheduleCloud {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        val employeeDto: TeacherDto? = null,
        val studentGroupDto: StudentGroupDto? =null,
        val schedules: Schedules,
        val exams: List<Schedule>,
        val startDate: String,
        val endDate: String,
        val startExamsDate: String? =null,
        val endExamsDate: String?=null
    ): ScheduleCloud{
        override fun <T> map(mapper: Mapper<T>): T {
          return mapper.map(
                employeeDto = employeeDto,
                studentGroupDto = studentGroupDto,
                schedules = schedules,
                exams = exams,
                startDate = startDate,
                endDate = endDate,
                startExamsDate = startExamsDate,
                endExamsDate = endExamsDate)
        }

    }
    data class Schedule(
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
        val dateLesson: String? = null,
        val employees: List<Employee>?= Collections.emptyList(),
        val startLessonDate: String
    )

    data class Schedules(
        @SerializedName("Понедельник")
        val Monday: List<Schedule>,
        @SerializedName("Вторник")
        val Tuesday: List<Schedule>,
        @SerializedName("Среда")
        val Wednesday: List<Schedule>,
        @SerializedName("Четверг")
        val Thursday: List<Schedule>,
        @SerializedName("Пятница")
        val Friday: List<Schedule>,
        @SerializedName("Суббота")
        val Saturday : List<Schedule>
    )

    data class StudentGroupDto (
        val name: String,
        val facultyID: Long,
        val facultyName: String,
        val course: Long,
        val id: Long,
        val calendarID: String
    )

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

    interface Mapper<T>{
        fun map(
             employeeDto: TeacherDto?,
             studentGroupDto: StudentGroupDto?,
             schedules: Schedules,
             exams: List<Schedule>,
             startDate: String,
             endDate: String,
             startExamsDate: String?,
             endExamsDate: String?
        ):T

        class Base: Mapper<ScheduleDomain>{
            override fun map(
                employeeDto: TeacherDto?,
                studentGroupDto: StudentGroupDto?,
                schedules: Schedules,
                exams: List<Schedule>,
                startDate: String,
                endDate: String,
                startExamsDate: String?,
                endExamsDate: String?,
            ):ScheduleDomain {
               val scheduleList  = listOf(
                   schedules.Monday?: emptyList(),
                   schedules.Tuesday?: emptyList(),
                   schedules.Wednesday?: emptyList(),
                   schedules.Tuesday?: emptyList(),
                   schedules.Saturday?: emptyList(),
               )
               return ScheduleDomain.Base(
                   name = employeeDto?.fio ?: studentGroupDto?.name ?:"",
                   schedules = scheduleList,
                   exams = exams)
            }

        }
    }
}