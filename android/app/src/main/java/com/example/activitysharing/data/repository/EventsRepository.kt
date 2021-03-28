package com.example.activitysharing.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.activitysharing.data.database.AppDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.database.model.EventWithUserImages
import com.example.activitysharing.data.database.model.asDomainModel
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventUserDisplayImageDao: EventUserDisplayImageDao,
    private val eventService: EventService
) {

    val events: LiveData<List<Event>> =
        Transformations.map(eventDao.getEventsWithUserDisplayImages()) {
            it.asDomainModel()
        }

    suspend fun refreshEvents() {
        withContext(Dispatchers.IO) {
            val events = eventService.fetchUpcomingEvents("haydn").asDatabaseModel()
            eventDao.insertAll(events.map { it.event })
            for (event in events) {
                eventUserDisplayImageDao.insertAll(event.eventUserDisplayImages)
            }
        }
    }
}