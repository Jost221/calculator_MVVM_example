package com.example.calculator.domain

object Engine {

    fun calculate(expression: String): String {
        return try {
            val result = eval(expression)
            // Если результат целый — убираем .0
            if (result == result.toLong().toDouble())
                result.toLong().toString()
            else
                result.toString()
        } catch (e: Exception) {
            "Ошибка"
        }
    }

    private fun eval(expression: String): Double {
        val tokens = expression
            .replace("×", "*")
            .replace("÷", "/")
            .trim()

        // Ищем + или - справа налево (низкий приоритет)
        var i = tokens.length - 1
        while (i >= 0) {
            val c = tokens[i]
            if ((c == '+' || c == '-') && i > 0) {
                return when (c) {
                    '+' -> eval(tokens.substring(0, i)) + eval(tokens.substring(i + 1))
                    '-' -> eval(tokens.substring(0, i)) - eval(tokens.substring(i + 1))
                    else -> throw Exception()
                }
            }
            i--
        }

        // Ищем * или / справа налево (высокий приоритет)
        i = tokens.length - 1
        while (i >= 0) {
            val c = tokens[i]
            if (c == '*' || c == '/') {
                return when (c) {
                    '*' -> eval(tokens.substring(0, i)) * eval(tokens.substring(i + 1))
                    '/' -> {
                        val right = eval(tokens.substring(i + 1))
                        if (right == 0.0) throw Exception("Деление на ноль")
                        eval(tokens.substring(0, i)) / right
                    }
                    else -> throw Exception()
                }
            }
            i--
        }

        // Просто число
        return tokens.toDouble()
    }
}