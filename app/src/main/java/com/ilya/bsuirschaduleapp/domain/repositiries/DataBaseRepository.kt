package com.ilya.bsuirschaduleapp.domain.repositiries

import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {

    suspend fun insertGroupInDB(group: Group)

    fun getGroupByNameFromDB(name: String): Flow<List<Group>>

    suspend fun deleteSelectedGroupFromDB(selectedGroup: SelectedGroup)

    suspend fun insertSelectedGroupInDB(selectedGroup: SelectedGroup)

    fun getSelectedGroupsFromDB(): Flow<List<SelectedGroup>>

    suspend fun insertTeacherInDB(teacher: Teacher)

    fun getTeacherByFioFromDB(fio: String): Flow<List<Teacher>>

    suspend fun deleteSelectedTeacherFromDB(selectedTeacher: SelectedTeacher)

    suspend fun insertSelectedTeacherInDB(selectedTeacher: SelectedTeacher)

    fun getSelectedTeachersFromDB(): Flow<List<SelectedTeacher>>
}