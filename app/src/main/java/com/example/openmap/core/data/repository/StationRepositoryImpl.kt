package com.example.openmap.core.data.repository

import com.example.openmap.core.data.converter.convertToDomain
import com.example.openmap.core.data.mapper.toDomain
import com.example.openmap.core.data.mapper.toEntity
import com.example.openmap.core.data.source.local.database.station.api.StationLocalSource
import com.example.openmap.core.data.source.remote.client.StationNetworkClient
import com.example.openmap.core.domain.api.StationRepository
import com.example.openmap.core.domain.model.Station
import com.google.gson.Gson
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val stationNetworkClient: StationNetworkClient,
    private val stationLocalSource: StationLocalSource,
    private val gson: Gson
) : StationRepository {

    override suspend fun getStations(): Result<List<Station>> {
        return stationNetworkClient.getStations()
            .map { response ->
                response.convertToDomain()
            }
            .onSuccess { result ->
                val entity = result.map { it.toEntity(gson) }
                stationLocalSource.saveStations(entity)
            }
    }

    override suspend fun getSavedStations(): List<Station> {
        return stationLocalSource.getStations().map { it.toDomain(gson) }
    }
}