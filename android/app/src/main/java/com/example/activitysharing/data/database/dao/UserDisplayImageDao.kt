package com.example.activitysharing.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.UserDisplayImage

@Dao
interface UserDisplayImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userDisplayImages: List<UserDisplayImage>)
}