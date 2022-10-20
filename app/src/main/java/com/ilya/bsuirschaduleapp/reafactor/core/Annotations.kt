package com.ilya.bsuirschaduleapp.reafactor.core

import javax.inject.Qualifier

/**
 * Created by HP on 20.09.2022.
 **/
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DomainErrorHandler()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UiErrorHandler()

