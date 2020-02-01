package com.future.bliblibazaar

import android.app.Application
import android.content.Context


class AppComponent : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        var instance: AppComponent? = null
            private set

        val context: Context?
            get() = instance
    }
}