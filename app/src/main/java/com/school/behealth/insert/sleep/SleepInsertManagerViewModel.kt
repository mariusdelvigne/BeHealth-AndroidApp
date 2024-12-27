package com.school.behealth.insert.sleep

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.sleep.dtos.CreateUserSleepCommand
import com.school.behealth.insert.sleep.dtos.CreateUserSleepResponse
import com.school.behealth.insert.sleep.repositories.IUserSleepRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SleepInsertManagerViewModel : ViewModel() {
    val mutableLiveUserSleepData: MutableLiveData<CreateUserSleepResponse> = MutableLiveData()
    private val userSleepRepository = RetrofitFactory.instance.create(IUserSleepRepository::class.java)

    fun insertUserSleep(userId: Int, command: CreateUserSleepCommand){
        viewModelScope.launch {
            try {
                val response = userSleepRepository.insertUserSleep(userId, command)
                mutableLiveUserSleepData.postValue(response)
            } catch (e: HttpException) {
                Log.e("SleepInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("SleepInsertManager", "Unexpected Error", e)
            }

        }
    }
}