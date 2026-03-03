package com.example.calculator.di

import com.example.calculator.data.local.CalculatorDatabase
import com.example.calculator.data.repository.Repository
import com.example.calculator.ui.history.HistoryViewModel
import com.example.calculator.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        CalculatorDatabase.getInstance(androidContext())
    }

    single {
        get<CalculatorDatabase>().calculationDao()
    }

    single {
        Repository(
            get()
        )
    }

    viewModel {
        MainViewModel(
            get()
        )
    }
    viewModel {
        HistoryViewModel(
            get()
        )
    }
}