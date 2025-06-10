package com.example.openmap.core.domain.model

data class Station(
    val cid: Int,
    val clients: Int,
    val connectTime: Int,
    val mountpoint: String,
    val pos: Position,
    val rtcm: Map<String, Int>,
    val userAgents: List<String> = listOf()
)