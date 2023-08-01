package androidx.compose.samples.crane.data

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBe
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class DestinationsRepositoryTest {

    val madrid = ExploreModel(
        city = MADRID,
        description = "Nonstop - 2h 12m+",
        imageUrl = "",
    )

    val khumbuvalley = ExploreModel(
        city = KHUMBUVALLEY,
        description = "Nonstop - 5h 16m+",
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
            cityName = MADRID.name,
        )

        destination shouldBe madrid
        destination shouldNotBe khumbuvalley
    }
}
