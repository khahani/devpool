package androidx.compose.samples.common.network.result

import androidx.compose.samples.common.result.Result
import androidx.compose.samples.common.result.asResult
import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.internal.assertEquals
import org.junit.Test
import kotlin.Exception

class ResultKtTest {
    @Test
    fun Result_catches_errors() = runTest {
        flow {
            emit(1)
            throw Exception("Test Done")
        }
            .asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(1), awaitItem())

                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "Test Done",
                        errorResult.exception?.message,
                    )

                    Result.Loading,
                    is Result.Success,
                    -> throw IllegalArgumentException(
                        "The flow should have emitted an Error Result",
                    )
                }

                awaitComplete()
            }
    }
}
