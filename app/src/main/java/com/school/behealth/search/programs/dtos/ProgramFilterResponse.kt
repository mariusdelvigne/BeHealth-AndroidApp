package com.school.behealth.search.programs.dtos

import java.util.Date

data class ProgramFilterResponse(
    val programs: List<ProgramFilter>,
)

data class ProgramFilter(
    val id: Int,
    val title: String,
    val privacy: String,
    val creationDatetime: Date,
    val description: String,
    val creatorId: Int,
    val creatorName: String,
    val sleepPlanId: Int?,
    val foodPlanId: Int?,
    val sportPlanId: Int?,
)