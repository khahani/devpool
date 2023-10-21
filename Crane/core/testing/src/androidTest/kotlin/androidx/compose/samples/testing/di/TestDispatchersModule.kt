package androidx.compose.samples.testing.di

import androidx.compose.samples.common.network.AppDispatchers.Default
import androidx.compose.samples.common.network.AppDispatchers.IO
import androidx.compose.samples.common.network.Dispatcher
import androidx.compose.samples.common.network.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
object TestDispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher =
        testDispatcher
}
