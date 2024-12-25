package com.school.behealth.shared.dtos.user.create

import java.util.Date

data class UserCreateResponse(
    val id: Int,
    val username: String,
    val mail: String,
    val role: String,
    val birthDate: Date,
    val gender: String,
    val name: String,
    val surname: String,
    val heightInCm: Int,
    val weightInG: Int
)