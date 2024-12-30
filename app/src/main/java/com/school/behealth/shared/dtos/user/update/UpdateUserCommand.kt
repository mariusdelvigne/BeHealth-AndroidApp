package com.school.behealth.shared.dtos.user.update

data class UpdateUserCommand(
    val username: String,
    val mail: String,
    val birthDate: String,
    val gender: String,
    val name: String,
    val surname: String
)