package com.example.openmap.core.data.source.local.database.common.di

import android.content.Context
import androidx.room.Room
import com.example.openmap.core.data.source.local.database.common.AppDatabase
import com.example.openmap.core.data.source.local.database.station.dao.StationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStationDao(database: AppDatabase): StationDao {
        return database.stationDao()
    }
}