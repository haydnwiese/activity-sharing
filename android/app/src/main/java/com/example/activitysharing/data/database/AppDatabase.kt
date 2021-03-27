package com.example.activitysharing.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.EventUserDisplayImage

@Database(entities = [DatabaseEvent::class, EventUserDisplayImage::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val eventDao: EventDao
    abstract val eventUserDisplayImageDao: EventUserDisplayImageDao
}

private lateinit var INSTANCE: AppDatabase

// TODO: Update to use dependency injection
fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "events").build()
        }
    }
    return INSTANCE
}