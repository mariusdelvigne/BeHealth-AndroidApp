package com.school.behealth.calculators.bmiCalculator.dtos

data class BmiCalculatorResponse(
    var bmi: Double,
    val bmiInterpretation: String
)
