package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ISessionRepository {
    @POST("session")
    suspend fun connectionSession(@Body command: SessionAuthenticateCommand): SessionDataResponse

    @DELETE("session")
    suspend fun deleteSession()

    @GET("session")
    suspend fun getSession()
}