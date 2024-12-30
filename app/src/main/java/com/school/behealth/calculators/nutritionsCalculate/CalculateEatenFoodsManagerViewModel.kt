package com.school.behealth.calculators.nutritionsCalculate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.foodApiCall.CreateFoodApiCallCommand
import com.school.behealth.shared.dtos.foodApiCall.CreateFoodApiCallResponse
import com.school.behealth.shared.repositories.IFoodApiCallRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class CalculateEatenFoodsManagerViewModel : ViewModel() {
    val mutableLiveCaloriesFoodEatenData: MutableLiveData<CreateFoodApiCallResponse> = MutableLiveData()
    private val eatenFoodsCaloriesRepository = RetrofitFactory.instance.create(IFoodApiCallRepository::class.java)

    fun calculateEatenFoodsCalories(command: CreateFoodApiCallCommand){
        viewModelScope.launch {
            try {
                val response = eatenFoodsCaloriesRepository.foodCalculatorCall(command)
                mutableLiveCaloriesFoodEatenData.postValue(response)
                Log.i("EatenFoodsCalculator", response.toString())
            } catch (e: Exception) {
                Log.e("CalculateBmiManager", "Error calculating BMI", e)
            }
        }
    }
}