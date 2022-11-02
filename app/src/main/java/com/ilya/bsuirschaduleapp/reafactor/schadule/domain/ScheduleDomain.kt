package com.ilya.bsuirschaduleapp.reafactor.schadule.domain



import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleUi
import java.util.*

/**
 * Created by HP on 24.10.2022.
 **/
interface ScheduleDomain {

    fun <T> map(mapper: Mapper<T>):T

    data class Base(
        val id: String,
        val name: String,
        val schedules: MutableList<MutableList<Schedule>>,
        val exams: MutableList<Schedule>
    ): ScheduleDomain{
        override fun <T> map(mapper: Mapper<T>): T {
          return mapper.map(id,name, schedules, exams)
        }

    }

    data class Schedule(
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
        val dateLesson: String,
        val employees:  String,
        val employeePhotoLink:String,
        val employeeFio: String
    )

    interface Mapper<T>{

        fun map(
            id: String,
            name: String,
            schedules: List<List<Schedule>>,
            exams: List<Schedule>): T


        class ToScheduleUi(
            private val subGroupRepository: SubGroupRepository.Read
        ): Mapper<ScheduleUi>{

            companion object{
                const val ALL_GROUP:String = "0"
            }

            override fun map(
                id: String,
                name: String,
                schedules: List<List<Schedule>>,
                exams: List<Schedule>,
            ):ScheduleUi {
               val subGroup = subGroupRepository.read()

                val newSchedule = emptyList<MutableList<Schedule>>().toMutableList()
                schedules.forEach { scheduleList->
                    val lessons: MutableList<Schedule> = emptyList<Schedule>().toMutableList()
                    scheduleList.forEach {
                            if(subGroup.toString() == ALL_GROUP) lessons.add(it)
                            else if (it.numSubgroup.toString() == ALL_GROUP || it.numSubgroup.toString() == subGroup.toString()) {
                                lessons.add(it)
                            }
                        }
                    newSchedule.add(lessons?: emptyList<Schedule>().toMutableList())
                }
                return ScheduleUi(name,newSchedule, exams)
            }
        }

        class ToScheduleCache: Mapper<List<ScheduleCache>>{
            override fun map(
                id: String,
                name: String,
                schedules: List<List<Schedule>>,
                exams: List<Schedule>,
            ): List<ScheduleCache> {
               val result = emptyList<ScheduleCache>().toMutableList()
                var weekDay=1
                schedules.forEach { dayOfWeek->
                    dayOfWeek.forEach {
                        result.add(
                            ScheduleCache(
                                id = 0,
                                dayOfWeek = weekDay,
                                weekNumber = it.weekNumber,
                                studentGroups = it.studentGroups,
                                numSubgroup = it.numSubgroup,
                                auditories = it.auditories?:"",
                                startLessonTime = it.startLessonTime,
                                endLessonTime = it.endLessonTime,
                                subject = it.subject,
                                subjectFullName = it.subjectFullName,
                                note = it.note,
                                lessonTypeAbbrev = it.lessonTypeAbbrev,
                                employees = it.employees?:"",
                                date = System.currentTimeMillis(),
                                employeePhotoLink = it.employeePhotoLink,
                                employeeFio = it.employeeFio
                            )
                        )
                    }
                    weekDay++
                }
                if(exams.isNotEmpty()){
                    exams.forEach {
                        ScheduleCache(
                            id = 0,
                            dayOfWeek = 0,
                            weekNumber = it.weekNumber,
                            studentGroups = it.studentGroups,
                            numSubgroup = it.numSubgroup,
                            auditories = it.auditories?:"",
                            startLessonTime = it.startLessonTime,
                            endLessonTime = it.endLessonTime,
                            subject = it.subject,
                            subjectFullName = it.subjectFullName,
                            note = it.note,
                            lessonTypeAbbrev = it.lessonTypeAbbrev,
                            employees = it.employees?:"",
                            date = System.currentTimeMillis(),
                            employeePhotoLink = it.employeePhotoLink,
                            employeeFio = it.employeeFio
                        )
                    }
                }
                return result
            }
        }

        class ToSelectedScheduleId: Mapper<String>{
            override fun map(
                id: String,
                name: String,
                schedules: List<List<Schedule>>,
                exams: List<Schedule>,
            ): String {
                return id
            }
        }


        class ToSelectedScheduleName: Mapper<String>{
            override fun map(
                id: String,
                name: String,
                schedules: List<List<Schedule>>,
                exams: List<Schedule>,
            ): String {
                return name
            }
        }
    }
}