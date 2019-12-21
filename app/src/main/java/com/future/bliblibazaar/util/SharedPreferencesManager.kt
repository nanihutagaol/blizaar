package com.future.bliblibazaar.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private fun getSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences("blibli_bazaar", Context.MODE_PRIVATE)
    }

    fun checkExistence(context: Context, key: String) = run {
        val spManager = getSharedPreferences(context)

        return@run spManager.contains(key)
    }

    fun putCache(context: Context, key: String, value: String) {
        val spManager = getSharedPreferences(context)
        val spEditor = spManager.edit()

        spEditor.putString(key, value)
        spEditor.apply()
    }

}