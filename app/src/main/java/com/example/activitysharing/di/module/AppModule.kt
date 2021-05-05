package com.example.activitysharing.di.module

import android.app.Application
import android.content.Context
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.network.UserService
import com.example.activitysharing.data.repository.EventsRepository
import com.example.activitysharing.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesEventsRepository(eventDao: EventDao,
                                 eventUserDisplayImageDao: EventUserDisplayImageDao,
                                 eventService: EventService): EventsRepository {
        return EventsRepository(eventDao, eventUserDisplayImageDao, eventService)
    }

    @Provides
    @Singleton
    fun providesUserRepository(userService: UserService): UserRepository {
        return UserRepository(userService)
    }
}