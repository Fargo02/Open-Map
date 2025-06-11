package com.example.openmap.core.data.source.local.database.station.api

import com.example.openmap.core.data.source.local.database.station.entity.StationEntity

interface StationLocalSource {

    suspend fun getStations(): List<StationEntity>

    suspend fun saveStations(stations: List<StationEntity>)
}
