package com.ilya.bsuirschaduleapp


import com.ilya.bsuirschaduleapp.reafactor.schadule.data.SubGroupRepository
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.CloudScheduleToDomainMapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cloud.ScheduleCloud
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomain
import com.ilya.bsuirschaduleapp.reafactor.schadule.domain.ScheduleDomainToUiMapper
import com.ilya.bsuirschaduleapp.reafactor.schadule.presentation.ScheduleUi
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val schedule = ScheduleCloud.Schedule(
        weekNumber = listOf(),
        studentGroups = listOf(),
        numSubgroup = 0,
        auditories = null,
        startLessonTime = "2",
        endLessonTime = "2",
        subject = "2",
        subjectFullName = "2",
        note = null,
        lessonTypeAbbrev = "2",
        dateLesson = null,
        employees = listOf(),
        startLessonDate = "2")

    val scheduleDomain = ScheduleDomain.Schedule(weekNumber = "",
        studentGroups = "",
        numSubgroup = 0L,
        auditories = null,
        startLessonTime = "2",
        endLessonTime = "2",
        subject = "2",
        subjectFullName = "2",
        note = null,
        lessonTypeAbbrev = "2",
        dateLesson = null,
        employees = null,
        startLessonDate = "2")

    val cloudSchedule = ScheduleCloud.Base(
        employeeDto = null,
        studentGroupDto = null,
        schedules = ScheduleCloud.Schedules(
            Monday = listOf(schedule),
            Tuesday = listOf(),
            Wednesday = listOf(),
            Thursday = listOf(),
            Friday = listOf(),
            Saturday = listOf()),
        exams = listOf(schedule),
        startDate = "2",
        endDate = "2",
        startExamsDate = null,
        endExamsDate = null)

    val domainObj: ScheduleDomain = ScheduleDomain.Base(
        name = "",
        schedules = listOf(listOf(scheduleDomain).toMutableList(),
          ).toMutableList(),
        exams = listOf(scheduleDomain).toMutableList()
    )

    val uiObj = ScheduleUi(
        name = "", schedules = listOf(listOf(scheduleDomain)), exams = listOf(scheduleDomain))
    @Test
    fun addition_isCorrect() {


        val cloudMapper = ScheduleCloud.Mapper.Base()

        val mapper = CloudScheduleToDomainMapper.Base(cloudMapper)

        val data = mapper.map(cloudSchedule)
        println(data)
        assertEquals(domainObj, data)
    }


    @Test
    fun domainToUi(){
        val mapper = ScheduleDomain.Mapper.ToScheduleUi(Fake())
        val toUiMapper = ScheduleDomainToUiMapper.Base(mapper)


       val result = toUiMapper.map(domainObj)
        assertEquals(uiObj,result)
    }
}

class Fake: SubGroupRepository.Read{



    override fun read(): Int = 0


}