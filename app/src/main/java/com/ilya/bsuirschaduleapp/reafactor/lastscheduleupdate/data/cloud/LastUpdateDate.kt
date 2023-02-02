package com.ilya.bsuirschaduleapp.reafactor.lastscheduleupdate.data.cloud

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HP on 01.11.2022.
 **/
data class LastUpdateDate(
    @SuppressLint("SimpleDateFormat")
    @SerializedName("lastUpdateDate" ) var lastUpdateDate : String?
    = SimpleDateFormat("dd.M.yyyy").format(Date()).toString()
)
