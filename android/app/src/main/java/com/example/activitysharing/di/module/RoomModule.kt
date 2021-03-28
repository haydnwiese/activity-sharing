package com.example.activitysharing.di.module

import android.content.Context
import androidx.room.Room
import com.example.activitysharing.data.database.AppDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            "events").build()
    }

    @Provides
    fun providesEventDao(appDatabase: AppDatabase): EventDao {
        return appDatabase.eventDao
    }

    @Provides
    fun providesEventUserDisplayImageDao(appDatabase: AppDatabase): EventUserDisplayImageDao {
        return appDatabase.eventUserDisplayImageDao
    }
}