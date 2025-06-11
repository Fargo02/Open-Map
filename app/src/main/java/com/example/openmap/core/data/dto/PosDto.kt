package com.example.openmap.core.data.dto

import com.google.gson.annotations.SerializedName

data class PosDto(
    @SerializedName("height")
    val height: Double = 0.0,
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lng")
    val lng: Double = 0.0
)