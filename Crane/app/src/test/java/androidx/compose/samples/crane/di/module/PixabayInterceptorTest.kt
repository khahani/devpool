package androidx.compose.samples.crane.di.module

import androidx.compose.samples.crane.data.api.CheckNetworkConfig
import androidx.compose.samples.crane.data.api.PixabayApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class PixabayInterceptorTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val testService by lazy {
        retrofit.create(PixabayApi::class.java)
    }

    private val testJson = """{ "key": 1 }"""

    @Test
    fun given_request_when_it_arrive_then_response_intercepted() = runTest {
        mockWebServer.enqueue(
            MockResponse().setBody(testJson).setResponseCode(200),
        )

        val checkNetworkConfig = testService.checkNetworkConfig()
        checkNetworkConfig shouldBeEqualTo CheckNetworkConfig(key = 1)
    }
}
