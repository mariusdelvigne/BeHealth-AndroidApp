package com.school.behealth.calculators.caloriesBurnedCalculator.dtos

data class CaloriesBurnedCalculatorCommand(
    val sport: String,
    val gender: String,
    val age: Int,
    val durationInMinute: Int,
    val heightInCm: Int,
    val weightInKg: Double,
)
