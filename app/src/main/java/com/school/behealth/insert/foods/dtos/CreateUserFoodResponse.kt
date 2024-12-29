package com.school.behealth.insert.foods.dtos

import java.util.Date

data class CreateUserFoodResponse(
    val id: Int,
    val name: String,
    val eatenDateTime: Date,
    val quantityInG: Int,
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
    val userId: Int,
    val foodId: Int
)
