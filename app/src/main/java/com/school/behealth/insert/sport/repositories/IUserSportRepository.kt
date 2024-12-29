package com.school.behealth.insert.sport.repositories

import com.school.behealth.insert.sport.dtos.CreateUserSportCommand
import com.school.behealth.insert.sport.dtos.CreateUserSportResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserSportRepository {
    @POST("users/{userId}/sports")
    suspend fun insertUserSport(@Path("userId") userId: Int, @Body command: CreateUserSportCommand): CreateUserSportResponse
}