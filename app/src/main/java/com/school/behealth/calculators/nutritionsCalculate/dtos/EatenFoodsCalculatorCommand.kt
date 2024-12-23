package com.school.behealth.calculators.nutritionsCalculate.dtos

data class EatenFoodsCalculatorCommand(
    val nameFood: String,
    val quantityInGrams: Int
)
