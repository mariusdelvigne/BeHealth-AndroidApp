package com.school.behealth.calculators.caloriesBurnedCalculator.dtos

data class CaloriesBurnedCalculatorResponse(
    val nameSport: String,
    val durationMinutes: Int,
    val calories: Int
)
