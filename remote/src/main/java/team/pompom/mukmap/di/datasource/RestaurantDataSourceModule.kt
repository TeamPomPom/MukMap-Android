package team.pompom.mukmap.di.datasource

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.datasource.RemoteRestaurantDataSourceImpl
import team.pompom.mukmap.gateway.remote.RemoteRestaurantDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RestaurantDataSourceModule {
    @Binds
    @Singleton
    fun bindsRestaurantDataSource(remoteRestaurantDataSourceImpl: RemoteRestaurantDataSourceImpl): RemoteRestaurantDataSource
}