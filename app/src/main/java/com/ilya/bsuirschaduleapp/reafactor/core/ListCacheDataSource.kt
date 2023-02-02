package com.ilya.bsuirschaduleapp.reafactor.core

/**
 * Created by HP on 23.09.2022.
 **/
interface ListCacheDataSource<T> : SuspendSave<List<T>>, SuspendFind<List<T>>