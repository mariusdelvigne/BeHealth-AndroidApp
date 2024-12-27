package com.school.behealth.insert.height

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.height.dtos.CreateUserHeightCommand
import com.school.behealth.insert.height.dtos.CreateUserHeightResponse
import com.school.behealth.insert.height.repositories.IUserHeightRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HeightInsertManagerViewModel : ViewModel() {
    val mutableLiveUserHeightData: MutableLiveData<CreateUserHeightResponse> = MutableLiveData()
    private val userHeightRepository = RetrofitFactory.instance.create(IUserHeightRepository::class.java)

    fun insertUserHeight(userId: Int, command: CreateUserHeightCommand){
        viewModelScope.launch {
            try {
                val response = userHeightRepository.insertUserHeight(userId, command)
                mutableLiveUserHeightData.postValue(response)
            } catch (e: HttpException) {
                Log.e("HeightInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("HeightInsertManager", "Unexpected Error", e)
            }

        }
    }
}