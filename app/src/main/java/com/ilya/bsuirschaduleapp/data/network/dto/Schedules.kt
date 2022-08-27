package com.ilya.bsuirschaduleapp.data.network.dto

import com.google.gson.annotations.SerializedName


data class Schedules(
    @SerializedName("Понедельник")
    val Monday: List<Schedule>,
    @SerializedName("Вторник")
    val Tuesday: List<Schedule>,
    @SerializedName("Среда")
    val Wednesday: List<Schedule>,
    @SerializedName("Четверг")
    val Thursday: List<Schedule>,
    @SerializedName("Пятница")
    val Friday: List<Schedule>,
    @SerializedName("Суббота")
    val Saturday : List<Schedule>
)
