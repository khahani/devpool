package androidx.compose.samples.core.network.fake

import JvmUnitTestFakeAssetManager
import androidx.compose.samples.core.network.model.NetworkCategory
import androidx.compose.samples.core.network.model.NetworkCenter
import androidx.compose.samples.core.network.model.NetworkCircle
import androidx.compose.samples.core.network.model.NetworkContext
import androidx.compose.samples.core.network.model.NetworkGeoBounds
import androidx.compose.samples.core.network.model.NetworkIcon
import androidx.compose.samples.core.network.model.NetworkLocation
import androidx.compose.samples.core.network.model.NetworkPlacesSearch
import androidx.compose.samples.core.network.model.NetworkResults
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class FakeFsqNetworkDataSourceTest {
    private lateinit var subject: FakeFsqNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        subject = FakeFsqNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
    }

    @Test
    fun testDeserializationOfPlacesSearch() = runTest(testDispatcher) {
        val expected = NetworkPlacesSearch(
            results = listOf(
                NetworkResults(
                    id = "52f18036498e071bbb86adf8",
                    categories = listOf(
                        NetworkCategory(
                            id = 13036,
                            name = "Tea Room",
                            icon = NetworkIcon(
                                prefix = "https://ss3.4sqi.net/img/categories_v2/food/tearoom_",
                                suffix = ".png",
                            ),
                        ),
                    ),
                    distance = 632,
                    link = "/v3/places/52f18036498e071bbb86adf8",
                    location = NetworkLocation(
                        address = "2003 Western Ave",
                        addressExtended = "Ste 109",
                        country = "US",
                        dma = "Seattle-Tacoma",
                        formattedAddress = "2003 Western Ave, Seattle, WA 98121",
                        locality = "Seattle",
                        postcode = "98121",
                        region = "WA",
                    ),
                    name = "Vital T Leaf",
                    timezone = "America/Los_Angeles",
                ),
            ),
            context = NetworkContext(
                geoBounds = NetworkGeoBounds(
                    circle = NetworkCircle(
                        center = NetworkCenter(
                            latitude = 47.606,
                            longitude = -122.349358,
                        ),
                        radius = 832,
                    ),
                ),
            ),
        )

        val response = subject.placesSearch()
        response.context shouldBeEqualTo expected.context
        response.results?.first() shouldBeEqualTo expected.results?.first()
    }
}
