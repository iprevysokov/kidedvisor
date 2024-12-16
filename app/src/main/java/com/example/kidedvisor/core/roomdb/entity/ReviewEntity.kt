package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    primaryKeys = [
        "profile_id",
        "club_id",
    ],
    indices = [Index(value = arrayOf("profile_id", "club_id"), unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profile_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ClubEntity::class,
            parentColumns = ["id"],
            childColumns = ["club_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class ReviewEntity(
    @ColumnInfo(name = "profile_id")
    val profileId: UUID,
    @ColumnInfo(name = "club_id")
    val clubId: UUID,
    val photo: String,
    val rating: Int,
    val date: String,
    val advantages: String,
    val disadvantages: String,
    @ColumnInfo(name = "comment_text")
    val commentText: String
)
