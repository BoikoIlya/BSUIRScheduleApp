package com.ilya.bsuirschaduleapp.reafactor.core

import android.util.Log

/**
 * Created by HP on 25.10.2022.
 **/
interface FetchDataInteractor<T,R> {

    suspend fun fetchData(
        atFinish:()->Unit,
        successful:(R)->Unit
    )

    suspend fun refresh(
        atFinish:()->Unit,
        successful:(R)->Unit
    )

    abstract class Abstract<T,R>(
        handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<T, R>,
        private val repository: RefreshRepository<T>
    ): FetchDataInteractor<T,R>,
        Interactor.Abstract(handleError,dispatchers) {

        override suspend fun  fetchData(
            atFinish: () -> Unit,
            successful: (R) -> Unit,
        )= handle(successful, atFinish){
            return@handle mapper.map(repository.fetchData())
        }

        override suspend fun refresh(
            atFinish: () -> Unit,
            successful: (R) -> Unit,
        ) = handle(successful, atFinish) {
            return@handle mapper.map(repository.refresh())
        }
    }
}