package com.example.openmap.core.data.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("cid")
    val cid: Int = 0,
    @SerializedName("clients")
    val clients: Int = 0,
    @SerializedName("connect_time")
    val connectTime: Int = 0,
    @SerializedName("mountpoint")
    val mountpoint: String = "",
    @SerializedName("pos")
    val pos: PosDto = PosDto(),
    @SerializedName("rtcm")
    val rtcm: Map<String, Int> = emptyMap(),
    @SerializedName("user_agents")
    val userAgents: List<String> = listOf()
)