package com.school.behealth.shared.dtos.session

data class SessionAuthenticateCommand(
    val username: String,
    var password: String
)
