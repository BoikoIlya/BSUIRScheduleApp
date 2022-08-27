package com.ilya.bsuirschaduleapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.models.SelectedGroup
import com.ilya.bsuirschaduleapp.domain.models.SelectedTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher

@Database(
    entities = [ SelectedGroup::class,Group::class, Teacher::class, SelectedTeacher::class],
    version = 1
)
abstract class ScheduleDB: RoomDatabase() {

    abstract fun getDao(): ScheduleDao
}