package com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.domain

import com.ilya.bsuirschaduleapp.reafactor.core.*
import com.ilya.bsuirschaduleapp.reafactor.favoriteTeachers.data.FavoriteRepository

/**
 * Created by HP on 27.10.2022.
 **/
interface FavoritesInteractor<T,R>: FetchDataInteractor<List<T>,List<R>> {

   abstract class Abstract<T, R>(
        private val repository: FavoriteRepository<T>,
        @UiErrorHandler
        private val handleError: HandleError,
        dispatchers: Dispatchers,
        private val mapper: Mapper<List<T>, List<R>>,
    ): FavoritesInteractor<T,R>
     {
         override suspend fun fetchData(atFinish: () -> Unit, successful: (List<R>) -> Unit) =
             successful(mapper.map(repository.favoritesList()))

         override suspend fun refresh(atFinish: () -> Unit, successful: (List<R>) -> Unit) = Unit
    }
}