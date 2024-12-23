package com.school.behealth.calculators.bmiCalculator.repositories

import com.school.behealth.calculators.bmiCalculator.dtos.BmiCalculatorCommand
import com.school.behealth.calculators.bmiCalculator.dtos.BmiCalculatorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IBmiCalculatorRepository {
    @POST("bmi-calculator")
    suspend fun calculateBmi(@Body command: BmiCalculatorCommand): BmiCalculatorResponse
}