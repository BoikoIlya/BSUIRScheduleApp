package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 19.09.2022.
 **/
interface Interactor {

    suspend fun<T> handle(
        successful: suspend (T)->Unit,
        atFinish: suspend ()->Unit,
        block: suspend ()->T
    )

    abstract class Abstract(
       private val handleError: HandleError,
       private val dispatchers: Dispatchers,
    ):Interactor{
        override suspend fun <T> handle(
            successful: suspend (T) -> Unit,
            atFinish: suspend () -> Unit,
            block: suspend () -> T
        ) {
            try {
                val result = block.invoke()
                dispatchers.changeToUI { successful.invoke(result) }
            }catch (e: Exception){
                handleError.handle(e)
            }finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }
        }



    }
}