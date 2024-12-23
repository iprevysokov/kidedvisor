package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ActivityTypeDao
import java.util.UUID

@Entity(
    tableName = ActivityTypeDao.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = ActivityBranchEntity::class,
        parentColumns = ["id"],
        childColumns = ["activity_branch_id"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
    )],
)
data class ActivityTypeEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "activity_branch_id")
    val activityBranchId: UUID,
    val name: String
)
