package com.ilya.bsuirschaduleapp.reafactor.core

import kotlinx.coroutines.flow.Flow

/**
 * Created by HP on 23.09.2022.
 **/
interface ListCacheDataSource<T> : Save<List<T>>,SuspendFind<Flow<List<T>>>