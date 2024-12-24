package com.school.behealth.search.programs.dtos

data class ProgramFilterQuery(
    val title: String? = "",
    val privacy: String? = "",
    val pageNumber: Int = 0,
    val pageSize: Int = 10,
)