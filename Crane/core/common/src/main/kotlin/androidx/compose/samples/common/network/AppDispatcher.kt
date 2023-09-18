package androidx.compose.samples.common.network

import javax.inject.Qualifier

@Qualifier
annotation class Dispatcher(val appDispatcher: AppDispatchers)

enum class AppDispatchers {
    Default,
    IO,
}
