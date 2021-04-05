package com.example.activitysharing.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.activitysharing.data.database.dao.EventDao
import com.example.activitysharing.data.database.dao.EventUserDisplayImageDao
import com.example.activitysharing.data.database.model.EventUserDisplayImage
import com.example.activitysharing.data.database.model.EventWithUserImages
import com.example.activitysharing.data.database.model.asDomainModel
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.network.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventUserDisplayImageDao: EventUserDisplayImageDao,
    private val eventService: EventService
) {

    private val _refreshStatus = MutableStateFlow(false)
    val refreshStatus: StateFlow<Boolean>
        get() = _refreshStatus

    val events: LiveData<List<Event>> =
        Transformations.map(eventDao.getEventsWithUserDisplayImages()) {
            it.asDomainModel()
        }

    // TODO: Update to return a result object to the ViewModel
    suspend fun refreshEvents() {
        _refreshStatus.value = true
        try {
            val events = eventService.fetchUpcomingEvents("haydn").asDatabaseModel()
            updateEventsDatabase(events)
            _refreshStatus.value = false
        } catch (throwable: Throwable) {
            // TODO: Implement better error handling
            Timber.d("Something went wrong")
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