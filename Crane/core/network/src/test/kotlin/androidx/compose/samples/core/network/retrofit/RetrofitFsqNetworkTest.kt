package androidx.compose.samples.core.network.retrofit

import JvmUnitTestFakeAssetManager
import androidx.compose.samples.core.network.fake.FakeAssetManager
import androidx.compose.samples.core.network.model.NetworkPlacesSearch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
class RetrofitFsqNetworkTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofitFsqNetwork: RetrofitFsqNetwork
    private lateinit var fakeAssetManager: FakeAssetManager
    private lateinit var networkJson: Json
    private val testDispatcher = StandardTestDispatcher()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val url = mockWebServer.url("/")
        networkJson = Json { ignoreUnknownKeys = true }
        retrofitFsqNetwork = RetrofitFsqNetwork(
            networkJson = networkJson,
            okhttpCallFactory = OkHttpClient.Builder().build(),
            url = url,
        )
        fakeAssetManager = JvmUnitTestFakeAssetManager
    }

    @Test
    fun test() = runTest(testDispatcher) {
        val responseBody: NetworkPlacesSearch =
            fakeAssetManager
                .open("places-search.json")
                .use(networkJson::decodeFromStream)

        mockWebServer.enqueue(
            MockResponse()
                .setBody(
                    networkJson.encodeToString(
                        NetworkPlacesSearch.serializer(),
                        responseBody,
                    ),
                ),
        )
        val response = retrofitFsqNetwork.placesSearch()
        Assert.assertEquals(responseBody, response)
    }
}
