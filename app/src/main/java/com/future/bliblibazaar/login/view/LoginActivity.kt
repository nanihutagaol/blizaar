package com.future.bliblibazaar.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.future.bliblibazaar.R
import com.future.bliblibazaar.activity.MainActivity
import com.future.bliblibazaar.databinding.ActivityLoginBinding
import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.viewmodel.LoginViewModel
import com.future.bliblibazaar.network.RetrofitClient
import com.future.bliblibazaar.register.model.User
import com.future.bliblibazaar.register.network.UserService
import com.future.bliblibazaar.register.view.RegisterActivity
import com.future.bliblibazaar.util.SharedPreferencesManager
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val log = LoggerFactory.getLogger(LoginActivity::class.java)
    private val userService: UserService = RetrofitClient.createUserService()
    private lateinit var mBinding: ActivityLoginBinding
    private val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mBinding.btLogin.setOnClickListener {
            val email = mBinding.etEmail.text.toString()
            val password = mBinding.etPassword.text.toString()

            loginViewModel.login(AuthenticateRequest(email, password))
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

    private fun register(email: String) {
        val request = User(email)

        userService.register(request).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Internal Server Error", Toast.LENGTH_SHORT)
                    .show()
                log.warn(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

            }
        })

    }

}