package com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.CloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import com.ilya.bsuirschaduleapp.reafactor.core.ListCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherCloud
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.teacherList.cloud.TeacherListService
import javax.inject.Inject

/**
 * Created by HP on 17.09.2022.
 **/


    interface TeacherListCloudDataSource: ListCloudDataSource<TeacherCloud> {

        class Base(
            private val teacherListService: TeacherListService,
            @DomainErrorHandler
            handleError: HandleError
        ) : TeacherListCloudDataSource, CloudDataSource.Abstract(handleError) {
            override suspend fun latestList() = handle {
                teacherListService.getListOfTeachers()
            }

        }
    }