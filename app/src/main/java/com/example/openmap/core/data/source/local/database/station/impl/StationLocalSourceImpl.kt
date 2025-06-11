package com.example.openmap.core.data.source.local.database.station.impl

import com.example.openmap.core.data.source.local.database.station.api.StationLocalSource
import com.example.openmap.core.data.source.local.database.station.dao.StationDao
import com.example.openmap.core.data.source.local.database.station.entity.StationEntity
import javax.inject.Inject

class StationLocalSourceImpl @Inject constructor(
    private val stationDao: StationDao,
) : StationLocalSource {

    override suspend fun getStations(): List<StationEntity> {
        return stationDao.getAll()
    }

    override suspend fun saveStations(stations: List<StationEntity>) {
        stationDao.insertAll(stations)
    }
}
