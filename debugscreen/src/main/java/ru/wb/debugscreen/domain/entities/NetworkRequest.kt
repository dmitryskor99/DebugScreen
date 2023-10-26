package ru.wb.debugscreen.domain.entities

data class NetworkRequest(
    val isSuccessful: Boolean,
    val url: String,
    val method: String,
    val code: Int,
    val request: NetworkInfo,
    val response: NetworkInfo,
)

data class NetworkInfo(
    val header: Map<String, List<String>>,
    val body: String?
)