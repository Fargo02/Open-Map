package com.example.openmap.core.data.source.remote.client

import com.example.openmap.core.data.dto.StationResponseDto
import com.example.openmap.core.data.source.remote.common.RetrofitNetworkClient

class StationNetworkClient(
    private val apiService: StationApiService
) : RetrofitNetworkClient() {

    suspend fun getStations(): Result<StationResponseDto> {
        return doRequest { apiService.getStation() }
    }
}