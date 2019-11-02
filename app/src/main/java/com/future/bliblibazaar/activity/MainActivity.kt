package com.future.bliblibazaar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.future.bliblibazaar.R
import com.future.bliblibazaar.fragment.BazaarFragment
import com.future.bliblibazaar.fragment.HistoryFragment
import com.future.bliblibazaar.network.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val userService = RetrofitClient.createUserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        loadFragment(BazaarFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener{

            when(it.itemId) {
                R.id.action_bazaar -> {
                    loadFragment(BazaarFragment())

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_history -> {
                    loadFragment(HistoryFragment())

                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }

        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {

        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()

            return true
        }

        return false
    }

}
