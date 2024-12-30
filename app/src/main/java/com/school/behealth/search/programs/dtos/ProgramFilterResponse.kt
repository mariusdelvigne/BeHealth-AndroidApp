package com.school.behealth.search.programs.dtos

data class ProgramFilterResponse(
    val programs: List<Program>,
)

data class Program(
    val id: Int,
    val title: String,
    val privacy: String,
    val creationDatetime: String,
    val description: String,
    val creatorId: Int,
    val creatorName: String,
    val sleepPlanId: Int?,
    val foodPlanId: Int?,
    val sportPlanId: Int?,
    var isFavorite: Boolean = false
)