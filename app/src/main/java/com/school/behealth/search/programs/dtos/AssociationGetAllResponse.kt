package com.school.behealth.search.programs.dtos

data class AssociationGetAllResponse(
    val astHealthProgramUsers: List<Association>,
)

data class Association(
    val id: Int,
    val program: AssociationProgram
)

data class AssociationProgram(
    val id: Int,
    val title: String,
    val description: String,
    val creatorId: Int,
    val creatorName: String,
)

