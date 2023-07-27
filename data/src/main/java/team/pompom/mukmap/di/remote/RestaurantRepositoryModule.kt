package team.pompom.mukmap.di.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.repository.RestaurantRepository
import team.pompom.mukmap.repository.RestaurantRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RestaurantRepositoryModule {
    @Binds
    @Singleton
    fun bindRestaurantRepositoryModule(restaurantRepositoryImpl: RestaurantRepositoryImpl): RestaurantRepository
}