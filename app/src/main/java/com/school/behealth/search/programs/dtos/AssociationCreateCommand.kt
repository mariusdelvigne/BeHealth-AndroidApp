package com.school.behealth.search.programs.dtos

data class AssociationCreateCommand(
    val programId: Int,
    val relationType: String
)