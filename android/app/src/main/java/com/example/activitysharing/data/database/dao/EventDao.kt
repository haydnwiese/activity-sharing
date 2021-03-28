package com.example.activitysharing.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.EventWithUserImages

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getEvents(): LiveData<List<DatabaseEvent>>

    @Transaction
    @Query("SELECT * FROM event")
    fun getEventsWithUserDisplayImages(): LiveData<List<EventWithUserImages>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<DatabaseEvent>)

    @Query("DELETE FROM Event WHERE Event.id NOT IN(:eventIds)")
    fun deleteOldEvents(eventIds: List<Long>)
}