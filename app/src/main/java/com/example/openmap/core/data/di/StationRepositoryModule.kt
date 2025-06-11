package com.example.openmap.core.data.di

import com.example.openmap.core.data.repository.StationRepositoryImpl
import com.example.openmap.core.domain.api.StationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StationRepositoryModule {

    @Binds
    fun bindStationRepository(
        stationRepositoryImpl: StationRepositoryImpl
    ): StationRepository
}