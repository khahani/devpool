package androidx.compose.samples.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPlacesSearch(
    val results: List<NetworkResults>? = null,
    val context: NetworkGeoBounds? = null,
)

@Serializable
data class NetworkGeoBounds(
    val circle: NetworkCircle? = null,
    val radius: Int? = null,
)

@Serializable
data class NetworkCircle(
    val latitude: Float? = null,
    val longitude: Float? = null,
)

@Serializable
data class NetworkResults(
    @SerialName("fsq_id")
    val id: String? = null,
    val categories: List<NetworkCategory>? = null,
    val distance: Int? = null,
    val link: String? = null,
    val location: NetworkLocation? = null,
    val name: String? = null,
    val timezone: String? = null,
)

@Serializable
data class NetworkLocation(
    val address: String? = null,
    @SerialName("address_extended")
    val addressExtended: String? = null,
    val country: String? = null,
    val dma: String? = null,
    @SerialName("formatted_address")
    val formattedAddress: String? = null,
    val locality: String? = null,
    val postcode: String? = null,
    val region: String? = null,
)

@Serializable
data class NetworkCategory(
    val id: Int? = null,
    val name: String? = null,
    val icon: NetworkIcon? = null,
)

@Serializable
data class NetworkIcon(
    val prefix: String? = null,
    val suffix: String? = null,
)
