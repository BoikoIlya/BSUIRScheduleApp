package com.ilya.bsuirschaduleapp.reafactor.GroupList.data.cloud

import com.ilya.bsuirschaduleapp.reafactor.core.CloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.core.DomainErrorHandler
import com.ilya.bsuirschaduleapp.reafactor.core.HandleError
import com.ilya.bsuirschaduleapp.reafactor.core.ListCloudDataSource
import com.ilya.bsuirschaduleapp.reafactor.teacherList.data.cloud.TeacherListCloudDataSource

/**
 * Created by HP on 08.10.2022.
 **/
interface GroupCloudDataSource: ListCloudDataSource<GroupCloud> {

    class Base(
        private val teacherListService: GroupListService,
        @DomainErrorHandler
        handleError: HandleError
    ) : GroupCloudDataSource, CloudDataSource.Abstract(handleError) {
        override suspend fun latestList() = handle {
           teacherListService.getListOfGroups()
        }

    }
}