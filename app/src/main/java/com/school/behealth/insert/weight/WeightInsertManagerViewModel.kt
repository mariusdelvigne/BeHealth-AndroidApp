package com.school.behealth.insert.weight

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.weight.dtos.CreateUserWeightCommand
import com.school.behealth.insert.weight.dtos.CreateUserWeightResponse
import com.school.behealth.insert.weight.repositories.IUserWeightRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class WeightInsertManagerViewModel : ViewModel() {
    val mutableLiveUserWeightData: MutableLiveData<CreateUserWeightResponse> = MutableLiveData()
    private val userWeightRepository =
        RetrofitFactory.instance.create(IUserWeightRepository::class.java)

    fun insertUserWeight(userId: Int, command: CreateUserWeightCommand) {
        viewModelScope.launch {
            try {
                val response = userWeightRepository.insertUserWeight(userId, command)
                mutableLiveUserWeightData.postValue(response)
            } catch (e: HttpException) {
                Log.e("WeightInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("WeightInsertManager", "Unexpected Error", e)
            }

        }
    }
}