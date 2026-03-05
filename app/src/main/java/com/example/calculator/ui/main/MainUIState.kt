package com.example.calculator.ui.main

data class UiState(
    val expression: String = "",
    val preview: String = "",
    val error: String? = null
)
