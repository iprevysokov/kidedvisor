package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.CommentDao
import java.util.UUID

@Entity(
    tableName = CommentDao.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ReviewEntity::class,
            parentColumns = ["profile_id", "club_id"],
            childColumns = ["profile_id", "club_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ]
)
data class CommentEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "profile_id")
    val profileId: UUID,
    @ColumnInfo(name = "club_id")
    val clubId: UUID,
    val date: Long, // дата в миллисекундах
    @ColumnInfo(name = "comment_text")
    val commentText: String
)
