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
interface ListInteractor<T, R>: FetchDataInteractor<List<T>,List<R>> {


    suspend fun find(query: String): List<R>

   abstract class Abstract <T, R>(
        private val repository: Repository<List<T>>,
        handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<List<T>,List<R>>,
    ): ListInteractor<T, R>, FetchDataInteractor.Abstract<List<T>,List<R>>(handleError,dispatchers,mapper,repository){

        override suspend fun find(query: String): List<R> {
           return mapper.map(repository.find(query))
        }

    }
}



