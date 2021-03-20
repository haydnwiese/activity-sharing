package com.example.activitysharing.data.model

import java.io.Serializable

data class Event(
    val name: String,
    val displayImage: String,
    val timeCreated: Long,
    val eventTime: Long,
    val createdBy: Long,
    val usersAttendingImages: List<String>
)