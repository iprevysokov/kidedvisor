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
            entity = ReviewEntity::class,
            parentColumns = ["profile_id"],
            childColumns = ["profile_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = ReviewEntity::class,
            parentColumns = ["club_id"],
            childColumns = ["club_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class CommentEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "profile_id")
    val profileId: UUID,
    @ColumnInfo(name = "club_id")
    val clubId: UUID,
    val date: Date,
    @ColumnInfo(name = "comment_text")
    val commentText: String
)
