package com.example.calculator.di

import com.example.calculator.data.local.CalculatorDatabase
import com.example.calculator.data.remote.ApiService
import com.example.calculator.data.repository.Repository
import com.example.calculator.ui.history.HistoryViewModel
import com.example.calculator.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        CalculatorDatabase.getInstance(androidContext())
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://ip-api.com/") 
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        get<CalculatorDatabase>().calculationDao()
    }

    single {
        Repository(
            dao = get(),
            api = get()
        )
    }

    viewModel { MainViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}
