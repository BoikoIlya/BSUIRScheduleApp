package com.ilya.bsuirschaduleapp.reafactor.GroupList.domain

import com.ilya.bsuirschaduleapp.reafactor.GroupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.teacherList.domain.ListInteractor
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 07.10.2022.
 **/
class BaseGroupListInteractor(
    private val repository: Repository<List<GroupListItemDomain>>,
    @UiErrorHandler
    private val handleError: HandleError,
    private val dispatchers: Dispatchers,
    private val mapper: Mapper<List<GroupListItemDomain>, List<GroupListItemUi>>,
): ListInteractor.Abstract<GroupListItemDomain, GroupListItemUi>(
    repository,
    handleError,
    dispatchers,
    mapper
)