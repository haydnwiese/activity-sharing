package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.model.NetworkEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventService @Inject constructor(private val api: EventAPI) {

    suspend fun fetchUpcomingEvents(userId: String): List<NetworkEvent> = withContext(Dispatchers.IO) {
            api.fetchUpcomingEvents(userId)
    }
}