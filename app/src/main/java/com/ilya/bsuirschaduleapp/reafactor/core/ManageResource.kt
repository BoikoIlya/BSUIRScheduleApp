package com.ilya.bsuirschaduleapp.reafactor.core

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

/**
 * Created by HP on 18.09.2022.
 **/
interface ManageResource {

    fun getString(@StringRes id: Int):String

    class Base @Inject constructor(
        private val context: Context
    ):ManageResource {
        override fun getString(id: Int): String = context.getString(id)
    }
}