package com.school.behealth.insert.sleep.dtos

data class CreateUserSleepResponse(
    val id: Int,
    val startDatetime: String,
    val endDatetime: String,
    val userId: Int
)
