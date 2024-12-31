package com.school.behealth.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.user.getById.UserGetByIdResponse
import com.school.behealth.shared.dtos.user.update.UpdateUserCommand
import com.school.behealth.shared.dtos.user.updatePassword.UpdatePasswordCommand
import com.school.behealth.shared.repositories.IUserPasswordRepository
import com.school.behealth.shared.repositories.IUserRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class SettingsUserManagerViewModel : ViewModel() {
    val mutableLiveResponseUserInformationData: MutableLiveData<String> = MutableLiveData()
    val mutableLiveUserInformationDate: MutableLiveData<UserGetByIdResponse> = MutableLiveData()
    private val userRepository = RetrofitFactory.instance.create(IUserRepository::class.java)

    val mutableLiveUserPasswordErrorData: MutableLiveData<String> = MutableLiveData()
    private val userPasswordRepository = RetrofitFactory.instance.create(IUserPasswordRepository::class.java)

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

    fun updateUserInformation(userId: Int, command: UpdateUserCommand) {
        viewModelScope.launch {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(dateFormat.parse(command.birthDate)!!)
                val updatedCommand = command.copy(birthDate = formattedDate)
                val response = userRepository.updateUserInformation(userId, updatedCommand)

                if (response.isSuccessful) {
                    mutableLiveResponseUserInformationData.postValue("User Information has been updated")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("UserInformation", "API error: $errorBody")
                    mutableLiveResponseUserInformationData.postValue("Error updating user password: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("UserInformation", "Unexpected error", e)
                mutableLiveResponseUserInformationData.postValue("Unexpected error occurred")
            }
        }
    }

    fun updateUserPassword(userId: Int, command: UpdatePasswordCommand) {
        viewModelScope.launch {
            try {
                val response = userPasswordRepository.updateUserPassword(userId, command)

                if (response.isSuccessful) {
                    mutableLiveUserPasswordErrorData.postValue("Password has been updated")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("UserInformation", "API error: $errorBody")
                    mutableLiveUserPasswordErrorData.postValue("Error updating user password: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("UserInformation", "Unexpected error", e)
                mutableLiveUserPasswordErrorData.postValue("Unexpected error occurred")
            }
        }
    }

}