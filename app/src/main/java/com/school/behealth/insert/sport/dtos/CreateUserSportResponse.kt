package com.school.behealth.insert.sport.dtos

import java.util.Date

data class CreateUserSportResponse(
    val id : Int,
    val name : String,
    val startDatetime : Date,
    val endDatetime : Date,
    val caloriesBurned : Int,
    val userIdv : Int,
)
