package com.school.behealth.search.programs.detailsProgramsFragment.dtos.plansDetails

data class PlansGetByIdResponse(
    val id: Int,
    val name: String,
    val category: String,
    val privacy: String,
    val creationDatetime: String,
    val description: String,
    val durationInDays: Int,
    val creatorId: Int,
)
