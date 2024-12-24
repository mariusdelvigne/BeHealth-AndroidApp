package com.school.behealth.home.signIn

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import com.school.behealth.shared.model.SessionManager
import com.school.behealth.shared.repositories.ISessionRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class SignInManagerViewModel : ViewModel() {
    private lateinit var context: Context
    private lateinit var session: SessionManager

    val mutableLiveSessionData: MutableLiveData<SessionDataResponse> = MutableLiveData()
    val mutableLiveErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isConnectedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val sessionRepository = RetrofitFactory.instance.create(ISessionRepository::class.java)

    fun setContext(context: Context) {
        this.context = context
        session = SessionManager(context)
    }

    fun verifyConnection() {
        viewModelScope.launch {
            try {
                sessionRepository.isConnected()
//                if (response.username != null) {
                    isConnectedLiveData.postValue(true)
//                }
            } catch (e: Exception) {
                mutableLiveErrorMessage.postValue("Authentication Error : ${e.message}")
                isConnectedLiveData.postValue(false)
            }
        }
    }

    fun createSession(command: SessionAuthenticateCommand) {
        viewModelScope.launch {
            try {
                val response = sessionRepository.connectionSession(command)
                mutableLiveSessionData.postValue(response)
                session.registerPref(response, command.password)
            } catch (e: Exception) {
                mutableLiveErrorMessage.postValue("Authentication Error : ${e.message}")
            }
        }
    }
}