package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
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
    @PrimaryKey
    @ColumnInfo(name = "profile_id")
    val profileId: UUID,
    @PrimaryKey
    @ColumnInfo(name = "club_id")
    val clubId: UUID,
    val photo: String,
    val rating: Int,
    val date: Date,
    val advantages: String,
    val disadvantages: String,
    @ColumnInfo(name = "comment_text")
    val commentText: String
)
