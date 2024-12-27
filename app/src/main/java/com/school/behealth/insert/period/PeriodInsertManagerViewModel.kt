package com.school.behealth.insert.period

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.period.dtos.CreateUserPeriodCommand
import com.school.behealth.insert.period.dtos.CreateUserPeriodResponse
import com.school.behealth.insert.period.repositories.IUserPeriodRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PeriodInsertManagerViewModel : ViewModel() {
    val mutableLiveUserPeriodData: MutableLiveData<CreateUserPeriodResponse> = MutableLiveData()
    private val userPeriodRepository = RetrofitFactory.instance.create(IUserPeriodRepository::class.java)

    fun insertUserPeriod(userId: Int, command: CreateUserPeriodCommand){
        viewModelScope.launch {
            try {
                val response = userPeriodRepository.insertUserPeriod(userId, command)
                mutableLiveUserPeriodData.postValue(response)
            } catch (e: HttpException) {
                Log.e("PeriodInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("PeriodInsertManager", "Unexpected Error", e)
            }

        }
    }
}