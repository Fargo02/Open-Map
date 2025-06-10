package com.example.openmap.map.domain.usecase.impl

import com.example.openmap.core.domain.api.StationRepository
import com.example.openmap.core.domain.model.Station
import com.example.openmap.map.domain.usecase.GetStationsUseCase
import javax.inject.Inject

class GetStationsUseCaseImpl @Inject constructor(
    private val stationRepository: StationRepository
) : GetStationsUseCase {

    override suspend fun invoke(): Result<List<Station>> {
        return stationRepository.getStations()
    }
}