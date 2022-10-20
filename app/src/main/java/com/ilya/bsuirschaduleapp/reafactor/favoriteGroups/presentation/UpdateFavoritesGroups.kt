package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 02.10.2022.
 **/

interface UpdateFavoritesGroups:
    Communication.MutableSingle<Boolean>,
    Communication.SuspendUpdate<Boolean>,
    Communication.Collector<Boolean> {

    interface Update: Communication.SuspendUpdate<Boolean>
    interface Collect: Communication.Collector<Boolean>
    interface Mutable: Communication.MutableSingle<Boolean>, Update, Collect

    class Base: Communication.SingleUiUpdate<Boolean>(),Update, Collect, Mutable
}
