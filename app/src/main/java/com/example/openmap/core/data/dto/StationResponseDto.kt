package com.example.openmap.core.data.dto

import com.google.gson.annotations.SerializedName

data class StationResponseDto(
    @SerializedName("clients_global")
    val clientsGlobal: Int = 0,
    @SerializedName("daily_clients_gl")
    val dailyClientsGl: Int = 0,
    @SerializedName("data")
    val data: List<DataDto> = listOf(),
    @SerializedName("handler_waiting")
    val handlerWaiting: Int = 0,
    @SerializedName("hourly_clients_gl")
    val hourlyClientsGl: Int = 0,
    @SerializedName("port")
    val port: Int = 0,
    @SerializedName("read_Kb")
    val readKb: Int = 0,
    @SerializedName("sources_global")
    val sourcesGlobal: Int = 0,
    @SerializedName("timestamp")
    val timestamp: Int = 0,
    @SerializedName("total_clients_gl")
    val totalClientsGl: Int = 0,
    @SerializedName("write_Kb")
    val writeKb: Int = 0
)