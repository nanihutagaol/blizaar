package com.future.bliblibazaar.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.future.bliblibazaar.auth.repositories.AuthRepository
import com.future.bliblibazaar.auth.view.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val authRepository = AuthRepository()

    protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String = "") {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (!tag.isEmpty()) {
            val prevFragment = supportFragmentManager.findFragmentByTag(tag)
            if (prevFragment != null) {
                fragmentTransaction.remove(prevFragment)
            }
        }

        fragmentTransaction.addToBackStack(null)
        if (!isFinishing) {
            dialogFragment.show(fragmentTransaction, tag)
        }
    }

    protected fun initFragmentStateLoss(f: Fragment, tag: String = "", id: Int) {
        try {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = supportFragmentManager.findFragmentByTag(tag)

            if (fragment != null) {
                val removeFragmentTransaction = supportFragmentManager.beginTransaction()

                removeFragmentTransaction.remove(fragment)
                removeFragmentTransaction.commitNowAllowingStateLoss()
            }

            fragmentTransaction.add(id, f, tag)
            fragmentTransaction.commitAllowingStateLoss()
        } catch (exception: Exception) {
            //Do nothing
        }
    }

    fun validateToken() {
        sharedPreferences = getSharedPreferences("blibli_bazaar", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("blizaar_token", "")

        CoroutineScope(Dispatchers.IO).launch {
            val response = authRepository.checkTokenValidation(token!!)

            withContext(Dispatchers.Main) {
                if (response.code == "401") {
                    intent = Intent(this@BaseActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    //Do nothing
                }
            }
        }
    }

}