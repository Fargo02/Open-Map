package com.example.openmap.core.data.source.local.database.station.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StationEntity(
    @PrimaryKey val mountpoint: String,
    val cid: Int,
    val clients: Int,
    val connectTime: Int,
    val lat: Double,
    val lng: Double,
    val height: Double,
    val rtcmJson: String,
    val userAgentsJson: String
)

