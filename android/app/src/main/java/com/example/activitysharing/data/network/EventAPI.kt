package com.example.activitysharing.data.network

import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.model.NetworkEvent
import retrofit2.http.GET
import retrofit2.http.Path

interface EventAPI {
    @GET("/upcoming-events/{userId}")
    suspend fun fetchUpcomingEvents(@Path("userId") userId: String): List<NetworkEvent>
}