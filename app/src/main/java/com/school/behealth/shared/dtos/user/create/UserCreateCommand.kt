package com.school.behealth.shared.dtos.user.create

data class UserCreateCommand(
    val mail: String,
    val username: String,
    val birthDate: String,
    val gender: String,
    val name: String,
    val surname: String,
    val password: String,
    val heightInCm: Int,
    val weightInG: Int
)