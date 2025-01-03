package com.school.behealth.shared.dtos.user.getById

data class UserGetByIdResponse (
    val id: Int,
    val username: String,
    val mail: String,
    val role: String,
    val birthDate: String,
    val gender: String,
    val name: String,
    val surname: String,
    val isBanned: Boolean
)