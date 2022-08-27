package com.ilya.bsuirschaduleapp.data.repositories

import com.ilya.bsuirschaduleapp.data.local.db.ScheduleDao
import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import com.ilya.bsuirschaduleapp.domain.repositiries.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(
    private val scheduleDao: ScheduleDao
): DataBaseRepository {

    override suspend fun insertSelectedGroupInDB(selectedGroup: SelectedGroup) {
        scheduleDao.insertSelectedGroup(selectedGroup)
    }

    override  fun getSelectedGroupsFromDB(): Flow<List<SelectedGroup>> {
        return scheduleDao.getGroupSelectedGroups()
    }

    override suspend fun insertTeacherInDB(teacher: Teacher) {
        scheduleDao.insertTeacher(teacher)
    }

    override fun getTeacherByFioFromDB(fio: String): Flow<List<Teacher>> {
        return scheduleDao.getTeacherByFIO(fio)
    }

    override suspend fun deleteSelectedTeacherFromDB(selectedTeacher: SelectedTeacher) {
        scheduleDao.deleteSelectedTeacher(selectedTeacher)
    }

    override suspend fun insertSelectedTeacherInDB(selectedTeacher: SelectedTeacher) {
        scheduleDao.insertSelectedTeacher(selectedTeacher)
    }

    override fun getSelectedTeachersFromDB(): Flow<List<SelectedTeacher>> {
        return scheduleDao.getSelectedTeachers()
    }

    override suspend fun insertGroupInDB(group: Group) {
        scheduleDao.insertGroup(group)
    }

    override fun getGroupByNameFromDB(name: String): Flow<List<Group>> {
        return scheduleDao.getGroupByName(name)
    }

    override suspend fun deleteSelectedGroupFromDB(selectedGroup: SelectedGroup) {
        scheduleDao.deleteSelectedGroup(selectedGroup)
    }
}