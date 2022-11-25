package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud

import androidx.compose.ui.res.stringResource
import com.google.gson.annotations.SerializedName
import com.ilya.bsuirschaduleapp.R
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
        val startDate: String? =null,
        val endDate: String? =null,
        val startExamsDate: String? =null,
        val endExamsDate: String?=null
    ): ScheduleCloud{
        override fun <T> map(mapper: Mapper<T>): T {
          return mapper.map(
                employeeDto = employeeDto,
                studentGroupDto = studentGroupDto,
                schedules = schedules,
                exams = exams,
                startDate = startDate?:"",
                endDate = endDate?:"",
                startExamsDate = startExamsDate,
                endExamsDate = endExamsDate)
        }

    }

    data class StudentGroup (
        val specialityName: String,
        val specialityCode: String,
        val numberOfStudents: Long,
        val name: String
    )

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
    ) {
        fun map(): ScheduleDomain.Schedule{
            return ScheduleDomain.Schedule(
                weekNumber = weekNumber.joinToString(),
                studentGroups = if(studentGroups!=null && studentGroups.isNotEmpty()){
                        var groups = "Гр. "
                        var counter = 0
                    for(it in studentGroups){
                        if(counter==2 && studentGroups.size>3) groups+=", ${it.name}..."
                        else if(counter==0) groups+="${it.name}"
                        else  groups+=", ${it.name}"
                       counter++
                       if(counter==3) break
                   }
                    groups
                } else "",
                numSubgroup = numSubgroup,
                auditories = if(auditories!=null && auditories.isNotEmpty()) auditories[0] else "",
                startLessonTime = startLessonTime,
                endLessonTime = endLessonTime,
                subject = subject,
                subjectFullName = subjectFullName,
                note = note?:"",
                lessonTypeAbbrev = lessonTypeAbbrev,
                dateLesson = dateLesson?:"",
                employees = if(employees!=null && employees.isNotEmpty()){
                    employees[0].lastName+
                            " "+employees[0].firstName+
                            " "+employees[0].middleName
                }
                else {
                     var groups = "Гр. "
                    var counter = 0
                    for(it in studentGroups){
                        if(counter==studentGroups.size) groups+=", ${it.name}..."
                        else if(counter==0) groups+="${it.name}"
                        else  groups+=", ${it.name}"
                        counter++
                    }
                    groups
                     },
                employeePhotoLink = if(employees!=null && employees.isNotEmpty())
                    employees[0].photoLink.toString() else "",
                employeeFio = if(employees!=null && employees.isNotEmpty()){
                    employees[0].lastName+
                            " "+employees[0].firstName[0]+"."+
                            " "+employees[0].middleName[0]+"." +
                            if(numSubgroup!=0L) " (подгр. ${numSubgroup})"
                            else ""
                }
                else ""
            )
        }
    }

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
                   schedules.Thursday?: emptyList(),
                   schedules.Friday?: emptyList(),
                   schedules.Saturday?: emptyList(),
               )
                val scheduleListDomain = emptyList<MutableList<ScheduleDomain.Schedule>>().toMutableList()
                var counter =0
                scheduleList.forEach {
                    val list = emptyList<ScheduleDomain.Schedule>().toMutableList()
                    for (schedule in it) {
                        list.add(schedule.map())
                    }
                    scheduleListDomain.add(list)
                counter++
                }
                val examsList = emptyList<ScheduleDomain.Schedule>().toMutableList()
                exams.forEach {examsList.add(it.map())}
               return ScheduleDomain.Base(
                   id = if(employeeDto!=null) employeeDto.urlId else if(studentGroupDto!=null) studentGroupDto.name else "",
                   name = if(employeeDto!=null)
                       employeeDto.lastName+" ${employeeDto.firstName[0]}. ${employeeDto.middleName[0]}."
                   else if(studentGroupDto!=null) studentGroupDto.name else "",
                   schedules = scheduleListDomain,
                   exams = examsList)
            }

        }
    }
}