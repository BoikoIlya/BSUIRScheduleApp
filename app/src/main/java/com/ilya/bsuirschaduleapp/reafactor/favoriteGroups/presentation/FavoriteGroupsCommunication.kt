package com.ilya.bsuirschaduleapp.reafactor.favoriteGroups.presentation

import com.ilya.bsuirschaduleapp.reafactor.groupList.presentation.GroupListItemUi
import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 02.10.2022.
 **/
interface FavoriteGroupsCommunication: Communication.Mutable<List<GroupListItemUi>> {

    class Base(defaultValue: List<GroupListItemUi>):
        Communication.UiUpdate<List<GroupListItemUi>>(defaultValue),
            FavoriteGroupsCommunication
}