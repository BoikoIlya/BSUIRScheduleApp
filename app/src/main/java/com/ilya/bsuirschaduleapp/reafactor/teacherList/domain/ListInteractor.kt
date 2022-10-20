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
interface ListInteractor<T, R> {

    suspend fun fetchData(
        atFinish:()->Unit,
        successful:(List<R>)->Unit
    )

    suspend fun find(query: String): Flow<List<R>>

   abstract class Abstract <T, R>(
        private val repository: Repository<List<T>>,
        handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<List<T>,List<R>>,
    ): ListInteractor<T, R>, Interactor.Abstract(handleError,dispatchers ){


        override suspend fun fetchData(
            atFinish: () -> Unit,
            successful: (List<R>) -> Unit,
        ) = handle(successful, atFinish){
         return@handle mapper.map(repository.fetchData(""))
        }

        override suspend fun find(query: String): Flow<List<R>> {
           return repository.find(query).map { mapper.map(it) }
        }
    }
}



