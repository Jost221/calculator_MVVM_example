package com.example.calculator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.repository.Repository
import com.example.calculator.domain.Engine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onButtonClick(label: String) {
        when (label) {
            "AC" -> clear()
            "⌫"  -> backspace()
            "="  -> evaluate()
            "+/-" -> negate()
            "%"  -> percent()
            else -> appendToExpression(label)
        }
    }

    private fun appendToExpression(label: String) {
        _uiState.update { state ->
            val newExpression = state.expression + label

            state.copy(
                expression = newExpression,
                preview = calcPreview(newExpression)
            )
        }
    }

    private fun evaluate() {
        viewModelScope.launch {
            val expression = _uiState.value.expression
            val result = repository.calculate(expression)

            _uiState.update { it.copy(
                expression = result,
                preview = ""
            )}
        }
    }

    private fun clear() {
        _uiState.update {
            it.copy(
                expression = "",
                preview = "",
                error = null
            )
        }
    }

    private fun backspace() {
        _uiState.update { state ->
            val newExpression = state.expression.dropLast(1)

            state.copy(
                expression = newExpression,
                preview = calcPreview(newExpression)
            )
        }
    }

    private fun negate() {
        _uiState.update { state ->
             val newExpression: String;
            if (state.expression.startsWith("-"))
                newExpression = state.expression.removePrefix("-")
            else
                newExpression = "-${state.expression}"

            state.copy(expression = newExpression)
        }
    }

    private fun percent() {
        _uiState.update { state ->
            val result = Engine.calculate(state.expression)

            var correctResult = result.toDoubleOrNull()
            if(correctResult == null){
                correctResult = 0.0
            }

            val percentValue = correctResult / 100
            state.copy(expression = percentValue.toString())
        }
    }

    private fun calcPreview(expression: String): String {
        if (expression.isEmpty())
            return ""
        val result = Engine.calculate(expression)
        if (result == "Ошибка")
            return ""
        else
            return result
    }
}
