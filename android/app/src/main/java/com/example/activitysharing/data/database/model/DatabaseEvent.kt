package com.example.activitysharing.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class DatabaseEvent(
    @PrimaryKey
    val id: Long,
    val eventName: String,
    val displayImageUrl: String,
    val timeCreated: Long,
    val eventTime: Long,
    val createdBy: Long,
    val numberAttending: Long
)