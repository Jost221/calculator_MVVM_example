package com.example.calculator.ui.history

import com.example.calculator.data.local.Entity

data class HistoryUiState(
    val calculations: List<Entity> = emptyList(),
    val isLoading: Boolean = true
)