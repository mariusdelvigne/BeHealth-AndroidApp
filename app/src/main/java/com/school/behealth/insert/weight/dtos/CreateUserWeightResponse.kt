package com.school.behealth.insert.weight.dtos

import java.util.Date

data class CreateUserWeightResponse(
    val id: Int,
    val weightInG: Int,
    val inputDate: Date,
    val userId: Int
)
