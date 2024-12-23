package com.school.behealth.calculators.caloriesBurnedCalculator.repositories

import com.school.behealth.calculators.caloriesBurnedCalculator.dtos.CaloriesBurnedCalculatorCommand
import com.school.behealth.calculators.caloriesBurnedCalculator.dtos.CaloriesBurnedCalculatorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ICaloriesBurnedCalculatorRepository {
    @POST("sport-calculator")
    suspend fun calculateCaloriesBurned(@Body command: CaloriesBurnedCalculatorCommand): CaloriesBurnedCalculatorResponse
}