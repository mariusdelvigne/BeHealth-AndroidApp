package com.school.behealth.shared.dtos.user.updatePassword

data class UpdatePasswordCommand (
    val currentPassword: String,
    val newPassword: String
)