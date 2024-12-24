package com.school.behealth.home.signIn

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import com.school.behealth.shared.repositories.ISessionRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class SignInManagerViewModel : ViewModel() {
    val mutableLiveSessionData: MutableLiveData<SessionDataResponse> = MutableLiveData()
    private val sessionRepository = RetrofitFactory.instance.create(ISessionRepository::class.java)

    fun createSession(command: SessionAuthenticateCommand){
        viewModelScope.launch {
            try {
                val response = sessionRepository.connectionSession(command)
                mutableLiveSessionData.postValue(response)
            } catch (e: Exception){
                Log.e("SessionAuth", "Error authentification user", e)
            }
        }
    }
}