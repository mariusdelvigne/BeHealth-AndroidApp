package com.school.behealth.calculators.nutritionsCalculate.dtos

data class EatenFoodsCalculatorResponse(
    val foodsName: String,
    val servingWeights: Int,
    val calories: Double,
    val totalFats: Double,
    val saturatedFats: Double,
    val cholesterol: Double,
    val sodium: Double,
    val totalCarbohydrates: Double,
    val dietaryFiber: Double,
    val sugars: Double,
    val proteins: Double,
    val potassium: Double
)
