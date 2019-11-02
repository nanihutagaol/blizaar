package com.future.bliblibazaar.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.future.bliblibazaar.R
import com.future.bliblibazaar.model.User
import com.future.bliblibazaar.network.RetrofitClient
import com.future.bliblibazaar.network.service.AuthService
import com.future.bliblibazaar.network.service.UserService
import com.future.bliblibazaar.webmodel.AuthenticateRequest
import com.future.bliblibazaar.webmodel.AuthenticateResponse
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val log = LoggerFactory.getLogger(LoginActivity::class.java)
    private val authService: AuthService = RetrofitClient.createAuthService()
    private val userService: UserService = RetrofitClient.createUserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEt = findViewById<EditText>(R.id.emailEt)
        val passwordEt = findViewById<EditText>(R.id.passwordEt)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        loginBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val password = passwordEt.text.toString()

            login(email, password)
        }

        registerBtn.setOnClickListener {

        }

    }

    private fun login(email: String, password: String) {
        val request = AuthenticateRequest(email, password)

        authService.login(request).enqueue(object: Callback<AuthenticateResponse> {
            override fun onFailure(call: Call<AuthenticateResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Internal Server Error", Toast.LENGTH_SHORT).show()
                log.warn(t.message)
            }

            override fun onResponse(
                call: Call<AuthenticateResponse>,
                response: Response<AuthenticateResponse>
            ) {

                if(response.isSuccessful) {
                    val cache = getSharedPreferences("blibli_bazaar", Context.MODE_PRIVATE)
                    val cacheEditor = cache.edit()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)

                    cacheEditor.putString("Token", response.body()!!.token)
                    cacheEditor.apply()

                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun register(email: String) {
        val request = User(email)

        userService.register(request).enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Internal Server Error", Toast.LENGTH_SHORT).show()
                log.warn(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {

            }
        })

    }

}