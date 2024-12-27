package com.school.behealth.insert.height.repositories

import com.school.behealth.insert.height.dtos.CreateUserHeightCommand
import com.school.behealth.insert.height.dtos.CreateUserHeightResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserHeightRepository {
    @POST("users/{userId}/heights")
    suspend fun insertUserHeight(@Path("userId") userId: Int, @Body command: CreateUserHeightCommand): CreateUserHeightResponse
}