package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event
import javax.inject.Inject

class EventService @Inject constructor(private val api: EventAPI) {

    suspend fun fetchUpcomingEvents(userId: String): List<Event> {
        return api.fetchUpcomingEvents(userId)
    }
}