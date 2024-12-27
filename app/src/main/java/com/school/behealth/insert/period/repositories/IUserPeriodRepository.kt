package com.school.behealth.insert.period.repositories

import com.school.behealth.insert.period.dtos.CreateUserPeriodCommand
import com.school.behealth.insert.period.dtos.CreateUserPeriodResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserPeriodRepository {
    @POST("users/{userId}/periods")
    suspend fun insertUserPeriod(@Path("userId") userId: Int, @Body command: CreateUserPeriodCommand): CreateUserPeriodResponse
}