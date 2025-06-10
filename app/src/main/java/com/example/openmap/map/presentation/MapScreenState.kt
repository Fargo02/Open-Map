package com.example.openmap.map.presentation

import com.example.openmap.core.domain.model.Station

data class MapScreenState(
    val stations: List<Station> = emptyList()
)