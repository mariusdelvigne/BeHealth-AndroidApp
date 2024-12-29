package com.school.behealth.insert.weight.repositories

import com.school.behealth.insert.weight.dtos.CreateUserWeightCommand
import com.school.behealth.insert.weight.dtos.CreateUserWeightResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserWeightRepository {
    @POST("users/{userId}/weights")
    suspend fun insertUserWeight(@Path("userId") userId: Int, @Body command: CreateUserWeightCommand): CreateUserWeightResponse
}