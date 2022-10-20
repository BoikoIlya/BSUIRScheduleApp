package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.room.*
import com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cloud.GroupCloud
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupListItemDomain.Base)

     /* @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertSelectedGroup(selectedGroup: SelectedGroup)*/

     @Query("SELECT * FROM groups_list WHERE name LIKE '%' || :name || '%'")
     fun getGroupByName(name: String): Flow<List<GroupListItemDomain.Base>>

     /*@Query("SELECT * FROM selected_group_column ")
     fun getGroupSelectedGroups(): Flow<List<SelectedGroup>>

     @Delete
     suspend fun deleteSelectedGroup(selectedGroup: SelectedGroup)*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: TeacherListItemDomain.Base)

   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertSelectedTeacher(selectedTeacher: SelectedTeacher)

    @Query("SELECT * FROM teacher_list_item_ui WHERE fio LIKE '%' || :fio || '%'")
    fun getTeacherByFIO(fio: String): Flow<List<TeacherListItemDomain.Base>>

   /* @Query("SELECT * FROM selected_teacher_column ")
    fun getSelectedTeachers(): Flow<List<SelectedTeacher>>

    @Delete
    suspend fun deleteSelectedTeacher(selectedTeacher: SelectedTeacher)*/

}