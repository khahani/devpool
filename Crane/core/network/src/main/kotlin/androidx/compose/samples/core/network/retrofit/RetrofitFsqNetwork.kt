package androidx.compose.samples.core.network.retrofit

import androidx.compose.samples.core.network.BuildConfig
import androidx.compose.samples.core.network.FsqNetworkDataSource
import androidx.compose.samples.core.network.model.NetworkPlacesSearch
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitFsqNetworkApi {
    @GET(value = "{version}/places/search")
    suspend fun placesSearch(
        @Path(value = "version", encoded = true) version: String = "v3",
        @Query("query") query: String? = null,
        @Query("ll") ll: String? = null,
        @Query("radius") radius: Int? = null,
        @Query("categories") categories: String? = null,
        @Query("chains") chains: String? = null,
        @Query("exclude_chains") excludeChains: String? = null,
        @Query("exclude_all_chains") excludeAllChains: Boolean? = null,
        @Query("fields") fields: String? = null,
        @Query("min_price") minPrice: Int? = null,
        @Query("max_price") maxPrice: Int? = null,
        @Query("open_at") openAt: String? = null,
        @Query("open_now") openNow: Boolean? = null,
        @Query("ne") ne: String? = null,
        @Query("sw") sw: String? = null,
        @Query("near") near: String? = null,
        @Query("polygon") polygon: String? = null,
        @Query("sort") sort: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("session_token") sessionToken: String? = null,
    ): NetworkPlacesSearch
}

private const val FSQ_BASE_URL = BuildConfig.FSQ_URL

@Singleton
class RetrofitFsqNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
    url: HttpUrl = FSQ_BASE_URL.toHttpUrl(), // todo: DI should handle it or else!
) : FsqNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(url)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitFsqNetworkApi::class.java)

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
        networkApi.placesSearch(
            query = query,
            ll = ll,
            radius = radius,
            categories = categories,
            chains = chains,
            excludeChains = excludeChains,
            excludeAllChains = excludeAllChains,
            fields = fields,
            minPrice = minPrice,
            maxPrice = maxPrice,
            openAt = openAt,
            openNow = openNow,
            ne = ne,
            sw = sw,
            near = near,
            polygon = polygon,
            sort = sort,
            limit = limit,
            sessionToken = sessionToken,
        )
}
