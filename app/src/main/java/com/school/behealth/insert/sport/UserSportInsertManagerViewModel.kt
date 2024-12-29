package com.school.behealth.insert.sport

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.insert.sport.dtos.CreateUserSportCommand
import com.school.behealth.insert.sport.dtos.CreateUserSportResponse
import com.school.behealth.insert.sport.repositories.IUserSportRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserSportInsertManagerViewModel : ViewModel() {
    val mutableLiveUserSportData: MutableLiveData<CreateUserSportResponse> = MutableLiveData()
    private val userSportRepository =
        RetrofitFactory.instance.create(IUserSportRepository::class.java)

    fun insertUserSport(userId: Int, command: CreateUserSportCommand) {
        viewModelScope.launch {
            try {
                val response = userSportRepository.insertUserSport(userId, command)
                mutableLiveUserSportData.postValue(response)
            } catch (e: HttpException) {
                Log.e("UserSportInsertManager", "HTTP Error: ${e.code()} - ${e.message()}", e)
            } catch (e: Exception) {
                Log.e("UserSportInsertManager", "Unexpected Error", e)
            }

        }
    }
}