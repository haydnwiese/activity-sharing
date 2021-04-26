package com.example.activitysharing.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.EventWithUserImages
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getEvents(): Flow<List<DatabaseEvent>>

    @Transaction
    @Query("SELECT * FROM event")
    fun getEventsWithUserDisplayImages(): Flow<List<EventWithUserImages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<DatabaseEvent>)

    @Query("DELETE FROM Event WHERE Event.id NOT IN(:eventIds)")
    fun deleteOldEvents(eventIds: List<Long>)
}