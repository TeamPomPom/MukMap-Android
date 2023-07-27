package team.pompom.mukmap.di.restaurant

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.dao.RestaurantDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantDaoModule {
    @Provides
    @Singleton
    fun provideRestaurantDao(baekDatabase: BaekDatabase): RestaurantDao {
        return baekDatabase.restaurantDao()
    }
}