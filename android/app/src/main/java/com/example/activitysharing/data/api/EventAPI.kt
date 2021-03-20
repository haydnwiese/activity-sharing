package com.example.activitysharing.data.api

import com.example.activitysharing.data.model.Event
import retrofit2.http.GET
import retrofit2.http.Path

interface EventAPI {
    @GET("/upcoming-events/{userId}")
    suspend fun fetchUpcomingEvents(@Path("userId") userId: String): List<Event>
}