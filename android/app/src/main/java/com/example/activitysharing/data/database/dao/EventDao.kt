package com.example.activitysharing.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.activitysharing.data.database.entity.DatabaseEvent

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getEvents(): LiveData<List<DatabaseEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<DatabaseEvent>)
}