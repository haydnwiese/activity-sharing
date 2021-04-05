package com.example.activitysharing.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.activitysharing.data.database.model.EventUserDisplayImage

@Dao
interface EventUserDisplayImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(eventUserDisplayImages: List<EventUserDisplayImage>)
}