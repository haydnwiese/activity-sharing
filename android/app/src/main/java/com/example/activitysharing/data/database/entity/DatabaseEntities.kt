package com.example.activitysharing.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
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
)

@Entity(tableName = "user_display_image",
    foreignKeys = [
        ForeignKey(entity = DatabaseEvent::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = CASCADE)])
data class UserDisplayImage(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "event_id")
    val eventId: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
