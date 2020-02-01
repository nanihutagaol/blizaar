package com.future.bliblibazaar.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.future.bliblibazaar.auth.view.LoginActivity
import com.future.bliblibazaar.util.SharedPreferencesManager

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent: Intent
        sharedPreferences = getSharedPreferences("blibli_bazaar", Context.MODE_PRIVATE)

        if(sharedPreferences.getString("blizaar_token", "")?.isNotEmpty() == true){
            intent = Intent(this, MainActivity::class.java)
        } else {
            intent = Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }

}