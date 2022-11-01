package com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache

import com.ilya.bsuirschaduleapp.reafactor.core.Mapper
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.SelectedScheduleIdDataSource
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SelectedScheduleIdRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SelectedScheduleNameRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain

/**
 * Created by HP on 26.10.2022.
 **/
interface ScheduleCacheToDomainMapper: Mapper<List<ScheduleCache>, ScheduleDomain> {

    abstract class Abstract(
       private val cachedId: SelectedScheduleIdRepository,
       private val cachedName: SelectedScheduleNameRepository,
    ): ScheduleCacheToDomainMapper{
        override fun map(data: List<ScheduleCache>): ScheduleDomain {
            val schedules = emptyList<MutableList<ScheduleDomain.Schedule>>().toMutableList()
            val exams = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list1 = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list2 = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list3 = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list4 = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list5 = emptyList<ScheduleDomain.Schedule>().toMutableList()
            val list6 = emptyList<ScheduleDomain.Schedule>().toMutableList()
           data.forEach {
               when(it.dayOfWeek) {
                   0->exams.add(map(it))
                   1-> list1.add(map(it))
                   2-> list2.add(map(it))
                   3-> list3.add(map(it))
                   4-> list4.add(map(it))
                   5-> list5.add(map(it))
                   6-> list6.add(map(it))
               }
           }
            schedules.add(list1)
            schedules.add(list2)
            schedules.add(list3)
            schedules.add(list4)
            schedules.add(list5)
            schedules.add(list6)
            return ScheduleDomain.Base(
                cachedId.read(),
                cachedName.read(),
                schedules,
                exams
            )
        }

        abstract fun map(data: ScheduleCache): ScheduleDomain.Schedule
    }

    class Base(
        cachedId: SelectedScheduleIdRepository,
        cachedName: SelectedScheduleNameRepository,
    ): Abstract(cachedId, cachedName){
        override fun map(data: ScheduleCache): ScheduleDomain.Schedule {
            return ScheduleDomain.Schedule(
                weekNumber = data.weekNumber,
                studentGroups = data.studentGroups,
                numSubgroup = data.numSubgroup,
                auditories = data.auditories,
                startLessonTime = data.startLessonTime,
                endLessonTime =data.endLessonTime,
                subject = data.subject,
                subjectFullName = data.subjectFullName,
                note = data.note,
                lessonTypeAbbrev = data.lessonTypeAbbrev,
                dateLesson = "",
                employees = data.employees,
                employeePhotoLink = data.employeePhotoLink,
                employeeFio = data.employeeFio
            )
        }

    }
}