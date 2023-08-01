package androidx.compose.samples.crane.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationsRepository @Inject constructor(
    private val destinationsLocalDataSource: DestinationsLocalDataSource,
) {
    val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
    val hotels: List<ExploreModel> = destinationsLocalDataSource.craneHotels
    val restaurants: List<ExploreModel> = destinationsLocalDataSource.craneRestaurants

    fun getDestination(cityName: String): ExploreModel? {
        // starting state assumption: |this| is not empty
        check(destinations.isNotEmpty()) {
            "destinations should not be empty."
        }
        // Argument assumption: cityName is not empty string
        require(cityName.isNotEmpty())

        val destination = destinationsLocalDataSource.craneDestinations.firstOrNull {
            it.city.name == cityName
        }

        // Ending state promise: the destination's city has the requested name.
        destination?.let {
            assert(it.city.name == cityName)
        } ?: assert(destination == null)

        return destination
    }
}
