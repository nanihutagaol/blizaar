package com.future.bliblibazaar.auth.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.future.bliblibazaar.R
import com.future.bliblibazaar.activity.MainActivity
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.viewmodel.LoginViewModel
import com.future.bliblibazaar.databinding.ActivityLoginBinding
import com.future.bliblibazaar.util.SharedPreferencesManager

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mBinding.btLogin.setOnClickListener {
            val email = mBinding.etEmail.text.toString()
            val password = mBinding.etPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill required information", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.login(AuthenticateRequest(email, password))
            }
        }

        mBinding.btRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)

            startActivity(intent)
        }

        loginViewModel.loginLiveData.observe(this, Observer {
            if (it.token.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT)
                    .show()
            } else {
                SharedPreferencesManager.putCache(this, "blizaar_token", it.token)

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        })
    }

}