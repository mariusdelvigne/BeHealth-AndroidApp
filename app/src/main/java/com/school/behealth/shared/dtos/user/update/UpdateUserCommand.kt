package com.school.behealth.shared.dtos.user.update

import java.util.Date

data class UpdateUserCommand(
    val username: String,
    val mail: String,
    val birthDate: Date,
    val gender: String,
    val name: String,
    val surname: String
)