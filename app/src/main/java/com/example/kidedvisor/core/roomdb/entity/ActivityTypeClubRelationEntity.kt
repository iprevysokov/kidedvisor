package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ActivityTypeClubRelationDao
import java.util.UUID

@Entity(
    tableName = ActivityTypeClubRelationDao.TABLE_NAME,
    primaryKeys = [
        "type_id",
        "club_id"
    ])
data class ActivityTypeClubRelationEntity(
    @ColumnInfo(name = "type_id")
    val typeId: UUID,
    @ColumnInfo(name = "club_id")
    val clubId: UUID
)
