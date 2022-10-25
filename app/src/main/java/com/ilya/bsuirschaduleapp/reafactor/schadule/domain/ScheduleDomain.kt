package com.ilya.bsuirschaduleapp.reafactor.schadule.domain


import com.ilya.bsuirschaduleapp.data.network.dto.StudentGroupDto
import com.ilya.bsuirschaduleapp.data.network.dto.TeacherDto
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleUi

/**
 * Created by HP on 24.10.2022.
 **/
interface ScheduleDomain {

    fun <T> map(mapper: Mapper<T>):T

    data class Base(
        val name: String,
        val schedules: List<List<ScheduleCloud.Schedule>>,
        val exams: List<ScheduleCloud.Schedule>
    ): ScheduleDomain{
        override fun <T> map(mapper: Mapper<T>): T {
          return mapper.map(name, schedules, exams)
        }

    }

    interface Mapper<T>{

        fun map(
            name: String,
            schedules: List<List<ScheduleCloud.Schedule>>,
            exams: List<ScheduleCloud.Schedule>): T

        class ToScheduleUi(
            private val subGroupRepository: SubGroupRepository.Read
        ): Mapper<ScheduleUi>{

            companion object{
                const val ALL_GROUP = 0L
            }

            override fun map(
                name: String,
                schedules: List<List<ScheduleCloud.Schedule>>,
                exams: List<ScheduleCloud.Schedule>,
            ):ScheduleUi {
               val subGroup = subGroupRepository.read()

                val newSchedule = emptyList<MutableList<ScheduleCloud.Schedule>>().toMutableList()
                val lessons: MutableList<ScheduleCloud.Schedule> = emptyList<ScheduleCloud.Schedule>().toMutableList()
                schedules.forEach { scheduleList->
                    scheduleList.forEach {
                       if(it.numSubgroup == ALL_GROUP || it.numSubgroup == subGroup.toLong()) {
                                lessons.add(it)
                            }
                    }
                    newSchedule.add(lessons?: emptyList<ScheduleCloud.Schedule>().toMutableList())
                    lessons.clear()
                }
                return ScheduleUi(name,newSchedule, exams)
            }
        }

        class ToScheduleCache: Mapper<List<ScheduleCache>>{
            override fun map(
                name: String,
                schedules: List<List<ScheduleCloud.Schedule>>,
                exams: List<ScheduleCloud.Schedule>,
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
                                auditories = it.auditories,
                                startLessonTime = it.startLessonTime,
                                endLessonTime = it.endLessonTime,
                                subject = it.subject,
                                subjectFullName = it.subjectFullName,
                                note = it.note,
                                lessonTypeAbbrev = it.lessonTypeAbbrev,
                                employees = it.employees,
                                date = System.currentTimeMillis()
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
                            auditories = it.auditories,
                            startLessonTime = it.startLessonTime,
                            endLessonTime = it.endLessonTime,
                            subject = it.subject,
                            subjectFullName = it.subjectFullName,
                            note = it.note,
                            lessonTypeAbbrev = it.lessonTypeAbbrev,
                            employees = it.employees,
                            date = System.currentTimeMillis()
                        )
                    }
                }
                return result
            }
        }

        class ToSelectedSchedule: Mapper<String>{
            override fun map(
                name: String,
                schedules: List<List<ScheduleCloud.Schedule>>,
                exams: List<ScheduleCloud.Schedule>,
            ): String {
                return name
            }

        }
    }
}