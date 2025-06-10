package com.example.openmap.map.di

import com.example.openmap.map.domain.usecase.GetStationsUseCase
import com.example.openmap.map.domain.usecase.impl.GetStationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapModule {

    @Binds
    fun bindGetStationsUseCaseImpl(
        getStationsUseCaseImpl: GetStationsUseCaseImpl
    ): GetStationsUseCase
}