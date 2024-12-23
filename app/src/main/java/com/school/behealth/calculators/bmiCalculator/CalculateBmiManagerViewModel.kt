package com.school.behealth.calculators.bmiCalculator

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.calculators.bmiCalculator.dtos.BmiCalculatorCommand
import com.school.behealth.calculators.bmiCalculator.dtos.BmiCalculatorResponse
import com.school.behealth.calculators.bmiCalculator.repositories.IBmiCalculatorRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class CalculateBmiManagerViewModel : ViewModel() {
    val mutableCalculateBmiLiveData: MutableLiveData<BmiCalculatorResponse> = MutableLiveData()
    private val bmiCalculatorRepository = RetrofitFactory.instance.create(IBmiCalculatorRepository::class.java)

    fun calculateBmi(command: BmiCalculatorCommand){
        viewModelScope.launch {
            try {
                val response = bmiCalculatorRepository.calculateBmi(command)
                val roundedBmi = BigDecimal(response.bmi).setScale(2, RoundingMode.HALF_UP).toDouble()
                response.bmi = roundedBmi
                mutableCalculateBmiLiveData.postValue(response)
            } catch (e: Exception) {
                Log.e("CalculateBmiManager", "Error calculating BMI", e)
            }
        }
    }
}