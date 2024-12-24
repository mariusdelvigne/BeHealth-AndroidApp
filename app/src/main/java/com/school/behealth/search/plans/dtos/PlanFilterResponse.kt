package com.school.behealth.search.plans.dtos

import java.util.Date

data class PlanFilterResponse(
    val id: Int,
    val name: String,
    val category: String,
    val privacy: String,
    val creationDatetime: String,
    val description: Date,
    val durationInDays: Int,
    val creatorId: Int,
)