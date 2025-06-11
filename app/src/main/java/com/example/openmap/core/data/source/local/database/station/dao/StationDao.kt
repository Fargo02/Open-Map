package com.example.openmap.core.data.source.local.database.station.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openmap.core.data.source.local.database.station.entity.StationEntity

@Dao
interface StationDao {

    @Query("SELECT * FROM stations")
    suspend fun getAll(): List<StationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stations: List<StationEntity>)
}
