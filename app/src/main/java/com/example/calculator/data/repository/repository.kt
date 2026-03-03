package com.example.calculator.data.repository

import com.example.calculator.data.local.Dao
import com.example.calculator.data.local.Entity
import com.example.calculator.domain.Engine
import kotlinx.coroutines.flow.Flow

class Repository(
    private val dao: Dao
) {
    val history: Flow<List<Entity>> = dao.getAll()
    suspend fun calculate(expression: String): String {
        val result = Engine.calculate(expression)

        if (result != "Ошибка") {
            dao.insert(
                Entity(
                    expression = expression,
                    result = result
                )
            )
        }

        return result
    }

    suspend fun clearHistory() {
        dao.deleteAll()
    }

    fun calculatePreview(expression: String): String {
        return Engine.calculate(expression)
    }
}

