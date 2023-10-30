package ru.wb.debugscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import ru.wb.debugscreen.interseptors.DebugInterceptor
import ru.wb.debugscreen.ui.debugScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_view)
        findViewById<View>(R.id.compose_view).debugScreen {
            TestRequest()
        }
    }
}

@Composable
private fun TestRequest() {
    val scope = rememberCoroutineScope()

    Column {
        Button(
            onClick = {
                scope.launch {
                    val api: TestApi =
                        Retrofit.Builder().baseUrl("https://rickandmortyapi.com").client(
                            OkHttpClient.Builder().addInterceptor(DebugInterceptor()).build()
                        ).build().create()
                    api.rickAndMorty()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "good test request")
        }
        Button(
            onClick = {
                scope.launch {
                    val api: TestApi =
                        Retrofit.Builder().baseUrl("https://rickandmortyapi.com").client(
                            OkHttpClient.Builder().addInterceptor(DebugInterceptor()).build()
                        ).build().create()
                    api.postRickAndMorty()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "test request")
        }
    }
}

interface TestApi {
    @GET("api")
    suspend fun rickAndMorty(
//        @Header("jkwnfed") hbwekf: String = "7777",
//        @Body body: RequestBody = RequestBody.create(null, "erlkjfn")
    ): retrofit2.Response<Unit>

    @GET("api/character")
    suspend fun postRickAndMorty(
        @Query("page") page: String = "19"
//        @Header("jkwnfed") hbwekf: String = "7777",
//        @Body body: RequestBody = RequestBody.create(null, "erlkjfn")
    ): retrofit2.Response<Unit>
}