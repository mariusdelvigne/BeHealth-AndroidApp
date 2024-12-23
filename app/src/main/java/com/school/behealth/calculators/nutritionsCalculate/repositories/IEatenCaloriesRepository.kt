package com.school.behealth.calculators.nutritionsCalculate.repositories

import com.school.behealth.calculators.nutritionsCalculate.dtos.EatenFoodsCalculatorCommand
import com.school.behealth.calculators.nutritionsCalculate.dtos.EatenFoodsCalculatorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IEatenCaloriesRepository {
    @POST("food-calculator")
    suspend fun calculateCaloriesEaten(@Body command: EatenFoodsCalculatorCommand): EatenFoodsCalculatorResponse
}