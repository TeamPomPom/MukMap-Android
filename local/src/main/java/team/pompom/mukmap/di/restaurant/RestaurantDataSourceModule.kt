package team.pompom.mukmap.di.restaurant

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.dao.RestaurantDao
import team.pompom.mukmap.datasource.RestaurantDataSourceImpl
import team.pompom.mukmap.gateway.local.LocalDataVersionDataSource
import team.pompom.mukmap.gateway.local.LocalRestaurantDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantDataSourceModule {
    @Provides
    @Singleton
    fun providesRestaurantDataSource(restaurantDao: RestaurantDao): LocalRestaurantDataSource {
        return RestaurantDataSourceImpl(restaurantDao = restaurantDao)
    }
}