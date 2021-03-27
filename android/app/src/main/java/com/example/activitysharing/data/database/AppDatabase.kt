package com.example.activitysharing.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.model.DatabaseEvent

@Database(entities = [DatabaseEvent::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val eventDao: EventDao
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