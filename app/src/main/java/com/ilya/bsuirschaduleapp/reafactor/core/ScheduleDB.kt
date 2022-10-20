package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilya.bsuirschaduleapp.reafactor.GroupList.domain.GroupListItemDomain
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.TeacherListItemDomain

@Database(
    entities = [TeacherListItemDomain.Base::class, GroupListItemDomain.Base::class],
    version = 1
)
abstract class ScheduleDB: RoomDatabase() {

    abstract fun getDao(): ScheduleDao
}