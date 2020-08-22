package com.en.tech

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate


/**
 * Created by Esraa Nayel on 8/18/2020.
 */

open class App : Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}
