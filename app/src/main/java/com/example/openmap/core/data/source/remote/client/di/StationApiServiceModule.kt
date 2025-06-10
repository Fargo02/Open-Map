package com.example.openmap.core.data.source.remote.client.di

import com.example.openmap.core.data.source.remote.client.StationApiService
import com.example.openmap.core.data.source.remote.client.StationNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StationApiServiceModule {

    @Provides
    fun provideStationApiService(
        retrofit: Retrofit
    ): StationApiService {
        return retrofit.create(StationApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideStationNetworkClient(
        stationApiService: StationApiService
    ): StationNetworkClient {
        return StationNetworkClient(stationApiService)
    }
}