package com.school.behealth.shared.model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.school.behealth.shared.dtos.SessionDataResponse

class SessionManager(context: Context) {
    private val sharedPref = context.getSharedPreferences("JWT", MODE_PRIVATE)

    private val username = sharedPref.getString("username", null)
    private val password = sharedPref.getString("password", null)
    private val role = sharedPref.getString("role", null)
    private val expirationDateTime = sharedPref.getString("expirationDateTime", null)
    private val userId = sharedPref.getString("userId", null)

    fun registerPref(data: SessionDataResponse, password: String){
        val editor = sharedPref.edit()
        editor.putString("userId", data.id.toString())
        editor.putString("username", data.username)
        editor.putString("password", password)
        editor.putString("role", data.role)
        editor.putString("expirationDateTime", data.tokenExpirationDateTime.toString())
        editor.apply()
    }

    fun printToken() {
        if (username != null && password !=null && role !=null && expirationDateTime !=null && userId !=null) {
            Log.i("JWT", username)
            Log.i("JWT", password)
            Log.i("JWT", role)
            Log.i("JWT", expirationDateTime)
            Log.i("JWT", userId)
        } else {
            Log.i("JWT", "Token is null")
        }
    }
}