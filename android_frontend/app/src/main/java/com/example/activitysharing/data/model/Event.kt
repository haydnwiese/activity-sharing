package com.example.activitysharing.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
    val id: Long,
    val name: String,
    val displayImage: String,
    val timeCreated: Long,
    val eventTime: Long,
    val createdBy: Long,
    val usersAttendingImages: List<String>
)