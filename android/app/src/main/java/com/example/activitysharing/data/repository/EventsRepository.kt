package com.example.activitysharing.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.example.activitysharing.data.database.AppDatabase
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.database.model.EventUserDisplayImage
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

    // TODO: Update to return a result object to the ViewModel
    fun refreshEvents(): LiveData<Boolean> = liveData(Dispatchers.IO) {
        emit(true)

        try {
            val events = eventService.fetchUpcomingEvents("haydn").asDatabaseModel()
            updateEventsDatabase(events)
            emit(false)
        } catch (throwable: Throwable) {
            // TODO: Implement better error handling
        }
    }

    private suspend fun updateEventsDatabase(events: List<EventWithUserImages>) {
        withContext(Dispatchers.IO) {
            eventDao.insertAll(events.map { it.event })

            val eventUserDisplayImages = arrayListOf<EventUserDisplayImage>()
            for (event in events) {
                eventUserDisplayImages.addAll(event.eventUserDisplayImages)
            }
            eventUserDisplayImageDao.insertAll(eventUserDisplayImages)

            val eventIds = events.map {
                it.event.id
            }
            eventDao.deleteOldEvents(eventIds)
        }
    }
}