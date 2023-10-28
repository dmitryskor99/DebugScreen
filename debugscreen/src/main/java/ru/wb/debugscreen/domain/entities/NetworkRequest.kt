package ru.wb.debugscreen.domain.entities

import android.annotation.SuppressLint
import ru.wb.debugscreen.domain.DebugScreenConfig
import java.text.SimpleDateFormat
import java.util.Date

data class NetworkRequest(
    val isSuccessful: Boolean,
    val timeSend: Date,
    val timeReceived: Long,
    val scheme: String,
    val host: String,
    val path: String,
    val queryParams: Map<String, String?>,
    val method: String,
    val codeRequest: Int,
    val request: NetworkInfo,
    val response: NetworkInfo,
) {
    @SuppressLint("SimpleDateFormat")
    val timeSendFormatted: CharSequence = (DebugScreenConfig.config.locale?.let {
        SimpleDateFormat(TIME_FORMAT, it)
    } ?: SimpleDateFormat(TIME_FORMAT)).format(timeSend)

    fun getFullUrl(): String = "$scheme://$host$path"

    companion object {
        private const val TIME_FORMAT = "HH:mm:ss dd.MM"
    }
}

data class NetworkInfo(
    val header: Map<String, String>,
    val body: String?
)