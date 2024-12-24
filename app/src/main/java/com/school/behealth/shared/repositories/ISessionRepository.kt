package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import retrofit2.HttpException
import retrofit2.Response
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
    suspend fun getSession()
}