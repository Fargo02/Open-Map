package com.example.openmap.core.data.source.local.database.station.di

import com.example.openmap.core.data.source.local.database.station.api.StationLocalSource
import com.example.openmap.core.data.source.local.database.station.impl.StationLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StationModule {

    @Binds
    fun bindStationLocalSource(
        stationLocalSourceImpl: StationLocalSourceImpl
    ): StationLocalSource
}