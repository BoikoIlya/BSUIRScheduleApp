package com.ilya.bsuirschaduleapp.data.local.db

import androidx.room.*
import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import com.ilya.bsuirschaduleapp.utils.Constance
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedGroup(selectedGroup: SelectedGroup)

    @Query("SELECT * FROM group_column WHERE name LIKE '%' || :name || '%'")
    fun getGroupByName(name: String): Flow<List<Group>>

    @Query("SELECT * FROM selected_group_column ")
    fun getGroupSelectedGroups(): Flow<List<SelectedGroup>>

    @Delete
    suspend fun deleteSelectedGroup(selectedGroup: SelectedGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: Teacher)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedTeacher(selectedTeacher: SelectedTeacher)

    @Query("SELECT * FROM teacher_column WHERE fio LIKE '%' || :fio || '%'")
    fun getTeacherByFIO(fio: String): Flow<List<Teacher>>

    @Query("SELECT * FROM selected_teacher_column ")
    fun getSelectedTeachers(): Flow<List<SelectedTeacher>>

    @Delete
    suspend fun deleteSelectedTeacher(selectedTeacher: SelectedTeacher)

}