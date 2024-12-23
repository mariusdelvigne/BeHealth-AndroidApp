package com.school.behealth.calculators.bmiCalculator.dtos

data class BmiCalculatorCommand(
    val gender: String,
    val age: Int,
    val heightInCm: Int,
    val weightInKg: Double
)
