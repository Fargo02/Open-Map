package com.example.openmap.core.data.repository

import com.example.openmap.core.data.convertor.convertToStation
import com.example.openmap.core.data.source.remote.client.StationNetworkClient
import com.example.openmap.core.domain.api.StationRepository
import com.example.openmap.core.domain.model.Station
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val stationNetworkClient: StationNetworkClient
) : StationRepository {

    override suspend fun getStations(): Result<List<Station>> {
        return stationNetworkClient.getStations().map { response ->
            response.convertToStation()
        }
    }
}