package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.room.*
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.GroupCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache.TeacherCache

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupCache)

    @Query("SELECT * FROM groups_list WHERE name LIKE '%' || :name || '%'")
    fun getGroupByName(name: String): List<GroupCache>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: TeacherCache)


    @Query("SELECT * FROM teacher_list_item_ui WHERE fio LIKE '%' || :fio || '%'")
    fun getTeacherByFIO(fio: String): List<TeacherCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(data: ScheduleCache)

    @Query("DELETE FROM schedules")
    suspend fun clearScheduleTable()

    @Query("SELECT * FROM schedules")
    fun getSchedules(): List<ScheduleCache>

}