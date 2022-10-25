package com.ilya.bsuirschaduleapp.reafactor.groupList.presentation

import com.ilya.bsuirschaduleapp.reafactor.core.Communication

/**
 * Created by HP on 09.10.2022.
 **/
interface GroupListCommunication: Communication.Mutable<List<GroupListItemUi>> {

    class Base(defaultValue: List<GroupListItemUi>):
        GroupListCommunication,
        Communication.UiUpdate<List<GroupListItemUi>>(defaultValue)
}