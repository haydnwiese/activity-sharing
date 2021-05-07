package com.example.activitysharing.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.database.model.EventUserDisplayImage
import com.example.activitysharing.data.database.model.EventWithUserImages
import com.example.activitysharing.data.database.model.asDomainModel
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.network.model.asDatabaseModel
import com.example.activitysharing.data.network.model.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventUserDisplayImageDao: EventUserDisplayImageDao,
    private val eventService: EventService
) {

    val upcomingEvents = eventDao.getEventsWithUserDisplayImages().map {
        it.asDomainModel()
    }

    suspend fun refreshUpcomingEvents() {
        val events = eventService.fetchUpcomingEvents("hdghg").asDatabaseModel()
        updateEventsDatabase(events)
    }

    suspend fun getEventsForUser(userId: Long) = eventService.fetchEventsForUser(userId).asDomainModel()

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