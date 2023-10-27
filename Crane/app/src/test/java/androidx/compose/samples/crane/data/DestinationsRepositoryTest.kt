package androidx.compose.samples.crane.data

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.util.UUID

internal class DestinationsRepositoryTest {

    val madrid = ExploreModel(
        city = City(
            name = UUID.randomUUID().toString(),
            country = "",
            latitude = "",
            longitude = "",
        ),
        description = "",
        imageUrl = "",
    )

    val khumbuvalley = ExploreModel(
        city = City(
            name = UUID.randomUUID().toString(),
            country = "",
            latitude = "",
            longitude = "",
        ),
        description = "",
        imageUrl = "",
    )

    val craneDestinations = listOf(
        khumbuvalley,
        madrid,
    )

    val destinationsLocalDataSource: DestinationsLocalDataSource = mock {
        on { craneDestinations } doReturn craneDestinations
    }

    val tested: DestinationsRepository = DestinationsRepository(
        destinationsLocalDataSource = destinationsLocalDataSource,
    )

    @Test
    fun getDestination() {
        val destination = tested.getDestination(
            cityName = madrid.city.name,
        )

        assertSame(destination, madrid)
        assertNotSame(destination, khumbuvalley)
    }
}
