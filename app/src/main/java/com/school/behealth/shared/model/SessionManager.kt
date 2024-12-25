package com.school.behealth.shared.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import com.school.behealth.shared.repositories.ISessionRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class SessionManager(
    context: Context,
    private val sessionRepository: ISessionRepository = RetrofitFactory.instance.create(
        ISessionRepository::class.java
    )
) : ViewModel() {

    val mutableLiveSessionData: MutableLiveData<SessionDataResponse> = MutableLiveData()
    val mutableLiveErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isConnectedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val sharedPref = context.getSharedPreferences("JWT", MODE_PRIVATE)

    fun getUsername(): String? = sharedPref.getString("username", null)
    fun getPassword(): String? = sharedPref.getString("password", null)
    fun getRole(): String? = sharedPref.getString("role", null)
    fun getUserId(): String? = sharedPref.getString("userId", null)

    fun registerPref(data: SessionDataResponse, password: String) {
        sharedPref.edit().apply {
            putString("userId", data.id.toString())
            putString("username", data.username)
            putString("password", password)
            putString("role", data.role)
            apply()
        }
    }

    fun deletePref() {
        sharedPref.edit().apply {
            remove("userId")
            remove("username")
            remove("password")
            remove("role")
            apply()
        }
    }

    fun verifyConnection() {
        viewModelScope.launch {
            try {
                sessionRepository.isConnected()
                isConnectedLiveData.postValue(true)
            } catch (e: Exception) {
                isConnectedLiveData.postValue(false)
                mutableLiveErrorMessage.postValue("Unable to connect: ${e.message}")
            }
        }
    }

    fun createSession(command: SessionAuthenticateCommand) {
        viewModelScope.launch {
            try {
                val response = sessionRepository.connectionSession(command)
                mutableLiveSessionData.postValue(response)
            } catch (e: Exception) {
                mutableLiveErrorMessage.postValue("Authentication Error: ${e.message}")
            }
        }
    }

    fun userAuthenticate() {
        val username = getUsername()
        val password = getPassword()
        if (username != null && password != null) {
            val commandAuthenticate = SessionAuthenticateCommand(username, password)
            createSession(commandAuthenticate)
        } else {
            mutableLiveErrorMessage.postValue("No user registered")
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            try {
                sessionRepository.deleteSession()
            } catch (e: Exception) {
                mutableLiveErrorMessage.postValue("Disconnection Error: ${e.message}")
            }
        }
    }

    fun printToken() {
        val username = getUsername()
        val role = getRole()
        val userId = getUserId()
        if (username != null && role != null && userId != null) {
            Log.i("JWT", "Token exists. Username: $username, Role: $role, UserID: $userId")
        } else {
            Log.i("JWT", "Token is null")
        }
    }
}
