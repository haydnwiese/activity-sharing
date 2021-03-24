package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event

class EventService() {
    private val api = ServiceBuilder.buildService(EventAPI::class.java)

    suspend fun fetchUpcomingEvents(userId: String): List<Event> {
        return api.fetchUpcomingEvents(userId)
    }
}