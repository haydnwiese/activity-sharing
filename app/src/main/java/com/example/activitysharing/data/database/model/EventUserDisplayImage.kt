package com.example.activitysharing.data.database.model

import androidx.room.*

@Entity(
    tableName = "user_display_image",
    foreignKeys = [
        ForeignKey(
            entity = DatabaseEvent::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index(value = ["event_id"])]
)
data class EventUserDisplayImage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "event_id")
    val eventId: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun List<EventUserDisplayImage>.asUrlList(): List<String> {
    return map {
        it.imageUrl
    }
}