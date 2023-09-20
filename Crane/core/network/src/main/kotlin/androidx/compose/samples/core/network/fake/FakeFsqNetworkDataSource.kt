package androidx.compose.samples.core.network.fake

import JvmUnitTestFakeAssetManager
import androidx.compose.samples.common.network.AppDispatchers.IO
import androidx.compose.samples.common.network.Dispatcher
import androidx.compose.samples.core.network.FsqNetworkDataSource
import androidx.compose.samples.core.network.model.NetworkPlacesSearch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * [FsqNetworkDataSource] implementation that provides static news resources to aid development
 */
class FakeFsqNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : FsqNetworkDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun placesSearch(
        query: String?,
        ll: String?,
        radius: Int?,
        categories: String?,
        chains: String?,
        excludeChains: String?,
        excludeAllChains: Boolean?,
        fields: String?,
        minPrice: Int?,
        maxPrice: Int?,
        openAt: String?,
        openNow: Boolean?,
        ne: String?,
        sw: String?,
        near: String?,
        polygon: String?,
        sort: String?,
        limit: Int?,
        sessionToken: String?,
    ): NetworkPlacesSearch =
        withContext(ioDispatcher) {
            assets.open(PLACES_SEARCH).use(networkJson::decodeFromStream)
        }

    companion object {
        private const val PLACES_SEARCH = "places-search.json"
    }
}
