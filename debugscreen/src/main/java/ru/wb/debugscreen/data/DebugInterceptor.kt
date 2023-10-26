package ru.wb.debugscreen.data

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import ru.wb.debugscreen.domain.entities.NetworkInfo
import ru.wb.debugscreen.domain.entities.NetworkRequest

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
            NetworkDataBaseService.upsertNetworkRequest(
                NetworkRequest(
                    isSuccessful = response.isSuccessful || response.isRedirect,
                    url = url.scheme() + "://" + url.host() + url.encodedPath(),
                    method = request.method(),
                    code = response.code(),
                    request = NetworkInfo(
                        header = request.headers().toMultimap(), body = requestBody
                    ),
                    response = NetworkInfo(
                        header = response.headers().toMultimap(),
                        body = responseBody
                    )
                )
            )
        }
        return response
    }
}