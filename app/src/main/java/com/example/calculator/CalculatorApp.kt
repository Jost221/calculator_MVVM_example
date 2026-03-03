package com.example.calculator

import android.app.Application
import com.example.calculator.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CalculatorApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CalculatorApp)
            modules(appModule)
        }
    }
}