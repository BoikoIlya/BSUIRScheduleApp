package com.ilya.bsuirschaduleapp.reafactor.teacherList.domain

import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi

/**
 * Created by HP on 07.10.2022.
 **/
class BaseTeacherListInteractor(
    private val repository: Repository<List<TeacherListItemDomain>>,
    @UiErrorHandler
    private val handleError: HandleError,
    private val dispatchers: Dispatchers,
    private val mapper: Mapper<List<TeacherListItemDomain>, List<TeacherListItemUi>>,
): ListInteractor.Abstract<TeacherListItemDomain, TeacherListItemUi>(
    repository,
    handleError,
    dispatchers,
    mapper
)