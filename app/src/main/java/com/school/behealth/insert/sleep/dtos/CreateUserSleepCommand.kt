package com.school.behealth.insert.sleep.dtos

data class CreateUserSleepCommand(
    val startDatetime: String,
    val endDatetime: String
)
