package com.example.openmap.core.domain.api

import com.example.openmap.core.domain.model.Station

interface StationRepository {

    suspend fun getStations(): Result<List<Station>>

    suspend fun getSavedStations(): List<Station>
}