package com.example.openmap.core.data.source.remote.client

import com.example.openmap.core.data.dto.StationResponseDto
import retrofit2.http.GET

interface StationApiService {

    @GET("/")
    suspend fun getStation(): StationResponseDto
}