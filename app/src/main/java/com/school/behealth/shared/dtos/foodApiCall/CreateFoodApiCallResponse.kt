package com.school.behealth.shared.dtos.foodApiCall

data class CreateFoodApiCallResponse(
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
    val potassium: Double,
    val photoFood: String
)
