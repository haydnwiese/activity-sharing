package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.model.NetworkEvent
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {
    @GET(NetworkPaths.eventFeed)
    suspend fun fetchUpcomingEvents(@Path(NetworkPaths.userIdParam) userId: String): List<NetworkEvent>

    @GET(NetworkPaths.userEvents)
    suspend fun fetchEventsForUser(@Path(NetworkPaths.userIdParam) userId: String): List<NetworkEvent>
}