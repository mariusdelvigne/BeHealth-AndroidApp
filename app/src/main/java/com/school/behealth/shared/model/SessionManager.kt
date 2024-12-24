package com.school.behealth.shared.model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE

class SessionManager(context: Context) {
    private val sharedPref = context.getSharedPreferences("JWT", MODE_PRIVATE)

    private val username = sharedPref.getString("username", null)
    private val password = sharedPref.getString("password", null)

    fun printToken() {
        if (username != null) {
            Log.i("JWT", username)
        } else {
            Log.i("JWT", "Token is null")
        }
    }
}