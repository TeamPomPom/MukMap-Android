package team.pompom.mukmap.di.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import team.pompom.mukmap.model.api.RestaurantApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantApiModule {
    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit) : RestaurantApi {
        return retrofit.create(RestaurantApi::class.java)
    }
}