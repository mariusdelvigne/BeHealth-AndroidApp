package com.school.behealth.shared.repositories

import com.school.behealth.shared.dtos.foodApiCall.CreateFoodApiCallCommand
import com.school.behealth.shared.dtos.foodApiCall.CreateFoodApiCallResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IFoodApiCallRepository {
    @POST("food-calculator")
    suspend fun foodCalculatorCall(@Body command: CreateFoodApiCallCommand): CreateFoodApiCallResponse
}