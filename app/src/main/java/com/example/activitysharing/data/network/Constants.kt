package com.example.activitysharing.data.network

object NetworkPaths {
    const val userIdParam = "userId"
    private const val users = "/users/{$userIdParam}"
    const val eventFeed = "$users/event-feed"
    const val userEvents = "$users/events"
    const val userProfile = "$users/profile"
}