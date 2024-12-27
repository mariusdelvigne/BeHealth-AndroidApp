package com.school.behealth.insert.height.dtos

import java.util.Date

data class CreateUserHeightResponse(
    val id: Int,
    val heightInCm: Int,
    val inputDate: Date,
    val userId: Int
)
