package com.example.activitysharing.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithUserImages(
    @Embedded
    val event: DatabaseEvent,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id"
    )
    val eventUserDisplayImages: List<EventUserDisplayImage>
)
