package com.school.behealth.shared.dtos.foodApiCall

data class CreateFoodApiCallCommand(
    val nameFood: String,
    val quantityInGrams: Int
)
