package com.future.bliblibazaar.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.future.bliblibazaar.auth.view.LoginActivity
import com.future.bliblibazaar.util.Constants

class SplashActivity : BaseActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent: Intent
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        var token = sharedPreferences.getString(Constants.TOKEN, "")

        intent = if (token!!.isNotEmpty()) {
            Intent(this@SplashActivity, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }

}