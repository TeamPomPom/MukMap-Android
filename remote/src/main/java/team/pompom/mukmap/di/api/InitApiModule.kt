package team.pompom.mukmap.di.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import team.pompom.mukmap.model.api.InitApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InitApiModule {
    @Provides
    @Singleton
    fun provideInitApi(
        retrofit: Retrofit
    ) : InitApi {
        return retrofit.create(InitApi::class.java)
    }
}