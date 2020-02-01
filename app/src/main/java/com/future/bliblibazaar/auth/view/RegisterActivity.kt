package com.future.bliblibazaar.auth.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.future.bliblibazaar.R
import com.future.bliblibazaar.auth.model.UserDto
import com.future.bliblibazaar.auth.viewmodel.RegisterViewModel
import com.future.bliblibazaar.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityRegisterBinding
    private val registerViewModel = RegisterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        mBinding.btRegister.setOnClickListener {
            val email = mBinding.etEmail.text.toString()

            if(email.isEmpty()) {
                Toast.makeText(this, "Please fill required information", Toast.LENGTH_SHORT).show();
            } else {
                registerViewModel.register(UserDto(email))
            }
        }

        registerViewModel.registerLiveData.observe(this, Observer {
            if(it.email.isNullOrEmpty()) {
                Toast.makeText(this@RegisterActivity, "Register failed", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@RegisterActivity, "Register success", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        })
    }
}