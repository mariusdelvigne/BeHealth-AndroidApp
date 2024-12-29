package com.school.behealth.insert.foods.repositories

import com.school.behealth.insert.foods.dtos.CreateUserFoodCommand
import com.school.behealth.insert.foods.dtos.CreateUserFoodResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserFoodRepository {
    @POST("users/{userId}/foods")
    suspend fun insertUserFood(@Path("userId") userId: Int, @Body command: CreateUserFoodCommand): CreateUserFoodResponse
}