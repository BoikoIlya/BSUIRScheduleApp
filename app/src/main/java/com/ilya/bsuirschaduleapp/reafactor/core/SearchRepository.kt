package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 22.09.2022.
 **/
interface SearchRepository<T> {

   suspend fun find(query: String):T
}