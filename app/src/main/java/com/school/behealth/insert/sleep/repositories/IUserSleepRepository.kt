package com.school.behealth.insert.sleep.repositories

import com.school.behealth.insert.sleep.dtos.CreateUserSleepCommand
import com.school.behealth.insert.sleep.dtos.CreateUserSleepResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserSleepRepository {
    @POST("users/{userId}/sleeps")
    suspend fun insertUserSleep(@Path("userId") userId: Int, @Body command: CreateUserSleepCommand): CreateUserSleepResponse
}