package com.example.calculator.data.repository

import android.util.Log
import com.example.calculator.data.local.Dao
import com.example.calculator.data.local.Entity
import com.example.calculator.data.remote.ApiService
import com.example.calculator.domain.Engine
import kotlinx.coroutines.flow.Flow

class Repository(
    private val dao: Dao,
    private val api: ApiService
) {
    val history: Flow<List<Entity>> = dao.getAll()

    suspend fun calculate(expression: String): String {
        val result = Engine.calculate(expression)
        val country = getCountryName()

        if (result != "Ошибка") {
            dao.insert(
                Entity(
                    expression = expression,
                    result = result,
                    country = country
                )
            )
        }
        return result
    }

    suspend fun getCountryName(): String {
        return try {
            val response = api.getCountry()
            response.country ?: "Неизвестно"
        } catch (e: java.net.UnknownHostException) {
            "Нет интернета"
        } catch (e: Exception) {
            "Ошибка получения"
        }
    }

    suspend fun clearHistory() {
        dao.deleteAll()
    }

    fun calculatePreview(expression: String): String {
        return Engine.calculate(expression)
    }
}
