package androidx.compose.samples.core.network

import androidx.compose.samples.core.network.model.NetworkPlacesSearch

interface FsqNetworkDataSource {
    suspend fun placesSearch(
        query: String? = null,
        ll: String? = null,
        radius: Int? = null,
        categories: String? = null,
        chains: String? = null,
        excludeChains: String? = null,
        excludeAllChains: Boolean? = null,
        fields: String? = null,
        minPrice: Int? = null,
        maxPrice: Int? = null,
        openAt: String? = null,
        openNow: Boolean? = null,
        ne: String? = null,
        sw: String? = null,
        near: String? = null,
        polygon: String? = null,
        sort: String? = null,
        limit: Int? = null,
        sessionToken: String? = null,
    ): NetworkPlacesSearch
}
