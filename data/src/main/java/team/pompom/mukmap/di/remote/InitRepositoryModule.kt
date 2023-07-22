package team.pompom.mukmap.di.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.repository.InitRepository
import team.pompom.mukmap.repository.InitRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InitRepositoryModule {
    @Binds
    @Singleton
    fun bindsInitRepositoryModule(initRepositoryImpl: InitRepositoryImpl) : InitRepository
}