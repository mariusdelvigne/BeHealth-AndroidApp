package com.school.behealth.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.user.getById.UserGetByIdResponse
import com.school.behealth.shared.repositories.IUserRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class SettingsUserManagerViewModel : ViewModel() {
    val mutableLiveUserInformationDate: MutableLiveData<UserGetByIdResponse> = MutableLiveData()
    private val userRepository = RetrofitFactory.instance.create(IUserRepository::class.java)

    fun getUserInformation(userId: Int){
        viewModelScope.launch {
            try {
                val response = userRepository.getUserById(userId)
                mutableLiveUserInformationDate.postValue(response)
            } catch (e: Exception) {
                Log.e("UserInformation", "Error get userInfo", e)
            }
        }
    }
}