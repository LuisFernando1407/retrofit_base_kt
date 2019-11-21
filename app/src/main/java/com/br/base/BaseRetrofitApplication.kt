package com.br.base

import android.app.Application
import android.annotation.SuppressLint
import android.content.Context
import timber.log.Timber

class BaseRetrofitApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        /* Config Timber */
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        /* Set instance */
        context = applicationContext
    }
}