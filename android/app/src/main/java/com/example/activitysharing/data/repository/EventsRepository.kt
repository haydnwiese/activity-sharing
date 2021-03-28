package com.example.activitysharing.data.repository

import com.example.activitysharing.data.database.AppDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.network.EventService
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventService: EventService) {

    

}