package com.example.activitysharing.data.network.model

import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.EventUserDisplayImage
import com.example.activitysharing.data.database.model.EventWithUserImages

class NetworkEvent(
    val id: Long,
    val eventName: String,
    val displayImageUrl: String,
    val timeCreated: Long,
    val eventTime: Long,
    val createdBy: Long,
    val usersAttendingPreviewUrls: List<String>
)

// TODO: Find a better way to do this
fun List<NetworkEvent>.asDatabaseModel(): List<EventWithUserImages> {
    return map { networkEvent ->
        val eventUserDisplayImages = networkEvent.usersAttendingPreviewUrls.map { url ->
            EventUserDisplayImage(
                id = 0,
                eventId = networkEvent.id,
                imageUrl = url
            )
        }
        EventWithUserImages(
            DatabaseEvent(
                id = networkEvent.id,
                eventName = networkEvent.eventName,
                displayImageUrl = networkEvent.displayImageUrl,
                timeCreated = networkEvent.timeCreated,
                eventTime = networkEvent.eventTime,
                createdBy = networkEvent.createdBy
            ),
            eventUserDisplayImages
        )
    }
}