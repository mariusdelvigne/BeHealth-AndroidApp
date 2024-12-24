package com.school.behealth.search.plans.dtos

data class PlanFilterResponse(
    val plans: List<Plan>
)

data class Plan(
    val id: Int,
    val name: String,
    val category: String,
    val privacy: String,
    val creationDatetime: String,
    val description: String,
    val durationInDays: Int,
    val creatorId: Int,
)