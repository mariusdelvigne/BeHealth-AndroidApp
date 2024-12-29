package com.school.behealth.insert.foods

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.foods.dtos.CreateUserFoodCommand
import com.school.behealth.insert.foods.dtos.CreateUserFoodResponse
import com.school.behealth.insert.foods.repositories.IUserFoodRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserFoodInsertManagerViewModel : ViewModel() {
    val mutableLiveUserFoodData: MutableLiveData<CreateUserFoodResponse> = MutableLiveData()
    private val userFoodRepository =
        RetrofitFactory.instance.create(IUserFoodRepository::class.java)

    fun insertUserFood(userId: Int, command: CreateUserFoodCommand) {
        viewModelScope.launch {
            try {
                val response = userFoodRepository.insertUserFood(userId, command)
                mutableLiveUserFoodData.postValue(response)
            } catch (e: HttpException) {
                Log.e("UserFoodInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("UserFoodInsertManager", "Unexpected Error", e)
            }

        }
    }
}