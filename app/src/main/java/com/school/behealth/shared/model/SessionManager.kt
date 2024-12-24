package com.school.behealth.shared.model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.school.behealth.shared.dtos.SessionAuthenticateCommand
import com.school.behealth.shared.dtos.SessionDataResponse
import com.school.behealth.shared.repositories.ISessionRepository
import com.school.behealth.utils.RetrofitFactory
import kotlinx.coroutines.launch

class SessionManager(context: Context): ViewModel() {
    val mutableLiveSessionData: MutableLiveData<SessionDataResponse> = MutableLiveData()
    val mutableLiveErrorMessage: MutableLiveData<String> = MutableLiveData()
    val isConnectedLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val sessionRepository = RetrofitFactory.instance.create(ISessionRepository::class.java)

    private val sharedPref = context.getSharedPreferences("JWT", MODE_PRIVATE)

    private val username = sharedPref.getString("username", null)
    private val password = sharedPref.getString("password", null)
    private val role = sharedPref.getString("role", null)
    private val userId = sharedPref.getString("userId", null)

    private fun registerPref(data: SessionDataResponse, password: String){
        val editor = sharedPref.edit()
        editor.putString("userId", data.id.toString())
        editor.putString("username", data.username)
        editor.putString("password", password)
        editor.putString("role", data.role)
        editor.apply()
    }

    fun verifyConnection() {
        viewModelScope.launch {
            try {
                sessionRepository.isConnected()
                isConnectedLiveData.postValue(true)
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
                registerPref(response, command.password)
            } catch (e: Exception) {
                mutableLiveErrorMessage.postValue("Authentication Error : ${e.message}")
            }
        }
    }

    fun printToken() {
        if (username != null && password !=null && role !=null && userId !=null) {
            Log.i("JWT", username)
            Log.i("JWT", password)
            Log.i("JWT", role)
            Log.i("JWT", userId)
        } else {
            Log.i("JWT", "Token is null")
        }
    }
}