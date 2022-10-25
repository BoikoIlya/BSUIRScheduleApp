package com.ilya.bsuirschaduleapp.reafactor.teacherList.domain

import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.teacherList.presentation.TeacherListItemUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Created by HP on 19.09.2022.
 **/
interface ListInteractor<T, R>: FetchDataInteractor<T,R> {


    suspend fun find(query: String): R

   abstract class Abstract <T, R>(
        private val repository: Repository<T>,
        handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<T,R>,
    ): ListInteractor<T, R>, FetchDataInteractor.Abstract<T,R>(handleError,dispatchers,mapper,repository){

        override suspend fun find(query: String): R {
           return mapper.map(repository.find(query))
        }

    }
}



