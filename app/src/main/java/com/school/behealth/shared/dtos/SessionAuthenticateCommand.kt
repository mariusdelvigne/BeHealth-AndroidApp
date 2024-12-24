package com.school.behealth.shared.dtos

data class SessionAuthenticateCommand(
    val username: String,
    val password: String
)
