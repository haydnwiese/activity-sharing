package com.example.activitysharing.data.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.activitysharing.data.domain.Event

data class EventWithUserImages(
    @Embedded
    val event: DatabaseEvent,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id"
    )
    val eventUserDisplayImages: List<EventUserDisplayImage>
)

fun List<EventWithUserImages>.asDomainModel(): List<Event> {
    return map {
        val event = it.event
        Event(
            event.id,
            event.eventName,
            event.displayImageUrl,
            event.timeCreated,
            event.eventTime,
            event.createdBy,
            it.eventUserDisplayImages.asUrlList()
        )
    }
}
