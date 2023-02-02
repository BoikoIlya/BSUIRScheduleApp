package com.ilya.bsuirschaduleapp.reafactor.groupList.domain

import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor

/**
 * Created by HP on 07.10.2022.
 **/

interface GroupListInteractor: ListInteractor<GroupListItemDomain, GroupListItemUi> {
    class Base(
        private val repository: Repository<List<GroupListItemDomain>>,
        @UiErrorHandler
        private val handleError: HandleError,
        private val dispatchers: Dispatchers,
        private val mapper: Mapper<List<GroupListItemDomain>, List<GroupListItemUi>>,
    ) : ListInteractor.Abstract<GroupListItemDomain, GroupListItemUi>(
        repository,
        handleError,
        dispatchers,
        mapper
    ), GroupListInteractor
}