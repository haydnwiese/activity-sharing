package com.example.activitysharing.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user_display_image",
    foreignKeys = [
        ForeignKey(entity = DatabaseEvent::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = ForeignKey.CASCADE
        )])
data class EventUserDisplayImage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "event_id")
    val eventId: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)