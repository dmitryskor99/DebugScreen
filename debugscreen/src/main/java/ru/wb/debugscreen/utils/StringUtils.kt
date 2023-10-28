package ru.wb.debugscreen.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun String.asJsonFormattedView(indent: Int = 4): String {
    return try {
        replace(" ", "")
            .replace("\n", "")
            .replace("\t", "")
            .replace("\r", "")
            .replace("\r\n", "")
            .parseAsJson(indent)


    } catch (e: RuntimeException) {
        this
    }
}

private fun String.parseAsJson(indent: Int): String {

    fun getSpaces(countIndent: Int): String = " ".repeat(countIndent * indent)

    if (isEmpty()) {
        throw RuntimeException()
    } else {
        val result = StringBuilder()
        var countIndent = 0
        forEach { char ->
            when (char) {
                '[', '{' -> {
                    countIndent++
                    val append = "${if (countIndent > 0) " " else ""} $char\n${getSpaces(countIndent)}"
                    result.append(append)
                }

                ']', '}' -> {
                    countIndent--
                    val append = "\n${getSpaces(countIndent)}$char"
                    result.append(append)
                }

                ',' -> {
                    result.append(char + "\n" + getSpaces(countIndent))
                }

                else -> {
                    result.append(char)
                }
            }
        }
        return result.toString()
    }
}

@Composable
fun String.getColorMethodNetwork(): Color {
    return when {
        contains("GET", ignoreCase = true) -> Color(0xFF377D3B)
        contains("POST", ignoreCase = true) -> Color(0xFFA57C33)
        contains("PUT", ignoreCase = true) -> Color(0xFF2152B2)
        contains("PATCH", ignoreCase = true) -> Color(0xFF5C3692)
        contains("DELETE", ignoreCase = true) -> Color(0xFF82261A)
        contains("HEAD", ignoreCase = true) -> Color(0xFF377D3B)
        contains("OPTIONS", ignoreCase = true) -> Color(0xFF982666)
        else -> MaterialTheme.colorScheme.primary
    }
}