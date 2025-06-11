package com.example.openmap.core.data.converter

import com.example.openmap.core.data.dto.StationResponseDto
import com.example.openmap.core.domain.model.Position
import com.example.openmap.core.domain.model.Station

fun StationResponseDto.convertToDomain(): List<Station> {
    return this.data.map { data ->
        Station(
            cid = data.cid,
            clients = data.clients,
            connectTime = data.connectTime,
            mountpoint = data.mountpoint,
            pos = Position(height = data.pos.height, lat = data.pos.lat, lng = data.pos.lng),
            rtcm = data.rtcm,
            userAgents = data.userAgents
        )
    }
}