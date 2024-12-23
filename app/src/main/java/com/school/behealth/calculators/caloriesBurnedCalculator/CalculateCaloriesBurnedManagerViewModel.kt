package com.school.behealth.calculators.caloriesBurnedCalculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.calculators.caloriesBurnedCalculator.dtos.CaloriesBurnedCalculatorCommand
import com.school.behealth.calculators.caloriesBurnedCalculator.dtos.CaloriesBurnedCalculatorResponse
import com.school.behealth.calculators.caloriesBurnedCalculator.repositories.ICaloriesBurnedCalculatorRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class CalculateCaloriesBurnedManagerViewModel : ViewModel() {
    val mutableLiveCaloriesBurnedData: MutableLiveData<CaloriesBurnedCalculatorResponse> = MutableLiveData()
    private val caloriesBurnedCalculatorRepository = RetrofitFactory.instance.create(ICaloriesBurnedCalculatorRepository::class.java)

    fun calculateCaloriesBurned(command: CaloriesBurnedCalculatorCommand){
        viewModelScope.launch {
            try {
                val response = caloriesBurnedCalculatorRepository.calculateCaloriesBurned(command)
                mutableLiveCaloriesBurnedData.postValue(response)
            } catch (e: Exception) {
                Log.e("CalculateBmiManager", "Error calculating BMI", e)
            }
        }
    }
}