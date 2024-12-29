package com.school.behealth.insert.foods.dtos

data class CreateUserFoodCommand(
    val name: String,
    val quantityInG: Int,
    val eatenDateTime: String
)
