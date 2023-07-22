package team.pompom.mukmap.di.datasource

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.datasource.RemoteInitDataSourceImpl
import team.pompom.mukmap.gateway.remote.RemoteInitDataSource
import team.pompom.mukmap.model.api.InitApi
import javax.inject.Singleton

/**
 * Init DI Module
 */

@Module
@InstallIn(SingletonComponent::class)
interface InitDataSourceModule {
    @Binds
    @Singleton
    fun bindsInitDataSourceModule(remoteInitDataSourceImpl: RemoteInitDataSourceImpl) : RemoteInitDataSource
}