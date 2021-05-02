package com.future.bliblibazaar.auth.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.R
import com.future.bliblibazaar.activity.BaseActivity
import com.future.bliblibazaar.activity.MainActivity
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.viewmodel.LoginViewModel
import com.future.bliblibazaar.databinding.ActivityLoginBinding
import com.future.bliblibazaar.util.Constants
import kotlinx.coroutines.*

class LoginActivity : BaseActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var sp: SharedPreferences
    private val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        sp = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val spEditor = sp.edit()

        mBinding.btLogin.setOnClickListener {
            val email = mBinding.etEmail.text.toString()
            val password = mBinding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill required information", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.viewModelScope.launch {
                    if (!loginViewModel.login(AuthenticateRequest(email, password))) {
                        Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        mBinding.btRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        loginViewModel.loginLiveData.observe(this, Observer {
            if (it.token.isNotEmpty()) {
                spEditor.putString(Constants.TOKEN, it.token)
                if (spEditor.commit()) {
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            delay(5000)
                        }
                    }
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        })
    }

}