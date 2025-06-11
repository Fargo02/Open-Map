package com.example.openmap.core.data.source.local.database.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openmap.core.data.source.local.database.station.dao.StationDao
import com.example.openmap.core.data.source.local.database.station.entity.StationEntity

@Database(
    entities = [StationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao
}