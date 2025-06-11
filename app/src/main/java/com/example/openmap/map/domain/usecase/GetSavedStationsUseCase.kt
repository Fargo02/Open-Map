package com.example.openmap.map.domain.usecase

import com.example.openmap.core.domain.model.Station

interface GetSavedStationsUseCase {

    suspend operator fun invoke(): List<Station>
}