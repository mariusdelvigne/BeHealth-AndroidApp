package com.school.behealth.search.programs.detailsProgramsFragment.dtos.programsDetails

data class ProgramsGetByIdResponse(
    val id: Int,
    val title: String,
    val privacy: String,
    val creationDatetime: String,
    val description: String,
    val creatorId: Int,
    val sleepPlanId: Int?,
    val foodPlanId: Int?,
    val sportPlanId: Int?,
)
