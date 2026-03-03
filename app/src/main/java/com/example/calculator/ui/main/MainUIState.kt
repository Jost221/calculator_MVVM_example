package com.example.calculator.ui.main

data class UiState(
    val expression: String = "",   // то что набирает пользователь
    val preview: String = "",      // предварительный результат
    val error: String? = null      // ошибка если есть
)