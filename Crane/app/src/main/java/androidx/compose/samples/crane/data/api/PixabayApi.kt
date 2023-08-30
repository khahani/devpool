package androidx.compose.samples.crane.data.api

import retrofit2.http.GET

interface PixabayApi {
    @GET("/")
    suspend fun checkNetworkConfig(): CheckNetworkConfig
}

data class CheckNetworkConfig(val key: Int)
