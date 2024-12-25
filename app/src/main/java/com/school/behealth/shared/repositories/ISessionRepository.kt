package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.session.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.session.SessionDataResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ISessionRepository {
    @POST("sessions")
    suspend fun connectionSession(@Body command: SessionAuthenticateCommand): SessionDataResponse

    @DELETE("sessions")
    suspend fun deleteSession()

    @GET("sessions")
    suspend fun isConnected(): SessionDataResponse
}