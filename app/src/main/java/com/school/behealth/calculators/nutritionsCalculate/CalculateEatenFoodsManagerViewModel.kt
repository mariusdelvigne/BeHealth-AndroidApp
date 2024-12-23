package com.school.behealth.calculators.nutritionsCalculate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.calculators.nutritionsCalculate.dtos.EatenFoodsCalculatorCommand
import com.school.behealth.calculators.nutritionsCalculate.dtos.EatenFoodsCalculatorResponse
import com.school.behealth.calculators.nutritionsCalculate.repositories.IEatenCaloriesRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class CalculateEatenFoodsManagerViewModel : ViewModel() {
    val mutableLiveCaloriesFoodEatenData: MutableLiveData<EatenFoodsCalculatorResponse> = MutableLiveData()
    private val eatenFoodsCaloriesRepository = RetrofitFactory.instance.create(IEatenCaloriesRepository::class.java)

    fun calculateEatenFoodsCalories(command: EatenFoodsCalculatorCommand){
        viewModelScope.launch {
            try {
                val response = eatenFoodsCaloriesRepository.calculateCaloriesEaten(command)
                mutableLiveCaloriesFoodEatenData.postValue(response)
            } catch (e: Exception) {
                Log.e("CalculateBmiManager", "Error calculating BMI", e)
            }
        }
    }
}