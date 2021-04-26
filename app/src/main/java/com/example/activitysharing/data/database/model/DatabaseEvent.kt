package com.example.activitysharing.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event")
data class DatabaseEvent(
        @PrimaryKey
        val id: Long,
        val eventName: String,
        val displayImageUrl: String?,
        val timeCreated: Date,
        val eventTime: Date,
        val createdBy: Long,
        val numberAttending: Int
)