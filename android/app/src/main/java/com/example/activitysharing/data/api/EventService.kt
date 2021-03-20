package com.example.activitysharing.data.api

import com.example.activitysharing.data.model.Event

class EventService() {
    private val api = ServiceBuilder.buildService(EventAPI::class.java)

    suspend fun fetchUpcomingEvents(userId: String): List<Event> {
        return api.fetchUpcomingEvents(userId)
    }
}