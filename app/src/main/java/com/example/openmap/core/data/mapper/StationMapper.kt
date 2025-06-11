package com.example.openmap.core.data.mapper

import com.example.openmap.core.data.source.local.database.station.entity.StationEntity
import com.example.openmap.core.domain.model.Position
import com.example.openmap.core.domain.model.Station
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Station.toEntity(gson: Gson): StationEntity {
    return StationEntity(
        mountpoint = mountpoint,
        cid = cid,
        clients = clients,
        connectTime = connectTime,
        lat = pos.lat,
        lng = pos.lng,
        height = pos.height,
        rtcmJson = gson.toJson(rtcm),
        userAgentsJson = gson.toJson(userAgents)
    )
}

fun StationEntity.toDomain(gson: Gson): Station {
    return Station(
        cid = cid,
        clients = clients,
        connectTime = connectTime,
        mountpoint = mountpoint,
        pos = Position(height = height, lat = lat, lng = lng),
        rtcm = gson.fromJson(rtcmJson, object : TypeToken<Map<String, Int>>() {}.type),
        userAgents = gson.fromJson(userAgentsJson, object : TypeToken<List<String>>() {}.type)
    )
}
