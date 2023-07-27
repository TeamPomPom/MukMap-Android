package team.pompom.mukmap.di.dataversion

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.dao.DataVersionDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataVersionDaoModule {
    @Provides
    @Singleton
    fun providesDataVersionDao(baekDatabase: BaekDatabase): DataVersionDao {
        return baekDatabase.dataVersionDao()
    }
}