package com.school.behealth.insert.period.dtos

import java.util.Date

data class CreateUserPeriodResponse(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val userId: Int
)
