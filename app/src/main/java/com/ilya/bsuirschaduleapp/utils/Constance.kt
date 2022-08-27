package com.ilya.bsuirschaduleapp.utils

object Constance {

    const val BASE_URL:String = "https://iis.bsuir.by"
    const val GROUP_SCHEDULE_DESTINATION_URL:String ="/api/v1/schedule"
    const val CURRENT_WEEK_DESTINATION_URL:String ="/api/v1/schedule/current-week"
    const val GROUP_LIST_DESTINATION_URL:String ="/api/v1/student-groups"
    const val TEACHER_LIST_DESTINATION_URL:String ="/api/v1/employees/all"
    const val TEACHER_SCHEDULE_DESTINATION_URL:String ="/api/v1/employees/schedule/{urlId}"

    const val SCHEDULE_DB: String = "schedule_db"
    const val SCHEDULE_DB_GROUP_COLUMN: String = "group_column"
    const val SCHEDULE_DB_SELECTED_GROUP_COLUMN: String = "selected_group_column"
    const val SCHEDULE_DB_TEACHER_COLUMN: String = "teacher_column"
    const val SCHEDULE_DB_SELECTED_TEACHER_COLUMN: String = "selected_teacher_column"

    const val CURRENT_WEEK_KEY: String = "curr_week"
    const val SELECTED_SUBGROUP_KEY: String = "selected_sub_group"
    const val SELECTED_GROUP_OR_TEACHER_PATH_FOR_API_KEY: String = "selected_group_or_teacher"
    const val SELECTED_GROUP_OR_TEACHER_NAME_KEY: String = "selected_group_or_teacher_name"
    const val DATA_STORE_NAME:String = "settings"
    const val DATA_STORE_DEF_VALUE:String = "0"

    const val IS_GROUP:Int = 1
    const val IS_TEACHER:Int = 2
    const val NOT_TEACHER_NOT_GROUP:Int =0
    const val ALL_GROUPS:Long = 0L


}