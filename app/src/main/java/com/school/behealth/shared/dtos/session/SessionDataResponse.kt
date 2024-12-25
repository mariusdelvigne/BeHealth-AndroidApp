package com.school.behealth.shared.dtos.session

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SessionDataResponse(
    val id: Int,
    val username: String,
    val role: String,
    @SerializedName("cookieExpiration")
    val tokenExpirationDateTime: Date
)