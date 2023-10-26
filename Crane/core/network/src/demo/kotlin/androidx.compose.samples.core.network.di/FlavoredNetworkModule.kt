package androidx.compose.samples.core.network.di

import androidx.compose.samples.core.network.FsqNetworkDataSource
import androidx.compose.samples.core.network.fake.FakeFsqNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: FakeFsqNetworkDataSource): FsqNetworkDataSource
}
