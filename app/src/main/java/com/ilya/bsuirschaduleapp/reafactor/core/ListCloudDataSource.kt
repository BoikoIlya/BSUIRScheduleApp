package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 08.10.2022.
 **/
interface ListCloudDataSource<T>: CloudDataSource {
    suspend fun latestList(): List<T>
}