package team.pompom.mukmap.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import team.pompom.mukmap.BaekDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    @Provides
    @Singleton
    fun providesBaekDataBase(
        @ApplicationContext context: Context
    ): BaekDatabase {
        return Room.databaseBuilder(
            context,
            BaekDatabase::class.java,
            BaekDatabase.DATABASE_NAME
        ).build()
    }
}