package com.school.behealth.search.plans.dtos

data class PlanFilterQuery(
    val name: String? = "",
    val category: String? =  "",
    val privacy: String? =  "",
    val pageNumber: Int = 0,
    val pageSize: Int = 10,
)
