package com.example.openmap.map.domain.usecase.impl

import com.example.openmap.core.domain.api.StationRepository
import com.example.openmap.core.domain.model.Station
import com.example.openmap.map.domain.usecase.GetSavedStationsUseCase
import javax.inject.Inject

class GetSavedStationsUseCaseImpl @Inject constructor(
    private val stationRepository: StationRepository
) : GetSavedStationsUseCase {

    override suspend fun invoke(): List<Station> {
        return stationRepository.getSavedStations()
    }
}