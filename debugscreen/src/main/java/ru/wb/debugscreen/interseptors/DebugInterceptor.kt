package ru.wb.debugscreen.interseptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer

class DebugInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestBody: String = try {
            val buffer = Buffer()
            request.body()?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: Exception) {
            "Error reading request body!"
        }
        val responseBody: String? = try {
            response.peekBody(Long.MAX_VALUE).string()
        } catch (e: Exception) {
            "Error reading response body!"
        }
        runBlocking {
            val url = request.url()
            val queries = url.queryParameterNames().associateWith { url.queryParameter(it) }
            val timeSend = response.sentRequestAtMillis()
            val timeReceived = response.receivedResponseAtMillis()
//            RequestDataBaseService.insertRequest(
//                NetworkRequest(
//                    isSuccessful = response.isSuccessful || response.isRedirect,
//                    timeSend = Date(timeSend),
//                    timeReceived = timeReceived - timeSend,
//                    scheme = url.scheme(),
//                    host = url.host(),
//                    path = url.encodedPath(),
//                    queryParams = queries,
//                    method = request.method(),
//                    codeRequest = response.code(),
//                    request = NetworkInfo(
//                        header = request.headers().toMultimap()
//                            .mapValues { it.value.joinToString(", ") },
//                        body = requestBody
//                    ),
//                    response = NetworkInfo(
//                        header = response.headers().toMultimap()
//                            .mapValues { it.value.joinToString(", ") },
//                        body = responseBody
//                    )
//                )
//            )
        }
        return response
    }
}