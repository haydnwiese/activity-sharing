package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.model.NetworkEvent
import javax.inject.Inject

class EventService @Inject constructor(private val api: EventAPI) {

    suspend fun fetchUpcomingEvents(userId: String): List<NetworkEvent> {
        return api.fetchUpcomingEvents(userId)
    }
}