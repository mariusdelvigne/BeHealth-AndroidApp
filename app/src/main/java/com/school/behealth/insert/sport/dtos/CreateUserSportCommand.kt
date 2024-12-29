package com.school.behealth.insert.sport.dtos

data class CreateUserSportCommand(
    val name: String,
    val startDateTime: String,
    val endDateTime: String
)
