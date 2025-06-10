package com.example.openmap.map.domain.usecase

import com.example.openmap.core.domain.model.Station

interface GetStationsUseCase {

    suspend operator fun invoke(): Result<List<Station>>
}