package com.ilya.bsuirschaduleapp.reafactor.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilya.bsuirschaduleapp.reafactor.groupList.data.cache.GroupCache
import com.ilya.bsuirschaduleapp.reafactor.schadule.data.cache.ScheduleCache
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cache.TeacherCache

@Database(
    entities = [TeacherCache::class, GroupCache::class, ScheduleCache::class],
    version = 1
)
abstract class ScheduleDB: RoomDatabase() {

    abstract fun getDao(): ScheduleDao
}