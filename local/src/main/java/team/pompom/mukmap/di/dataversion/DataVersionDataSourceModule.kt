package team.pompom.mukmap.di.dataversion

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.dao.DataVersionDao
import team.pompom.mukmap.datasource.DataVersionDataSourceImpl
import team.pompom.mukmap.gateway.local.LocalDataVersionDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataVersionDataSourceModule {
    @Provides
    @Singleton
    fun providesDataVersionDataSource(dataVersionDao: DataVersionDao): LocalDataVersionDataSource {
        return DataVersionDataSourceImpl(dataVersionDao = dataVersionDao)
    }
}