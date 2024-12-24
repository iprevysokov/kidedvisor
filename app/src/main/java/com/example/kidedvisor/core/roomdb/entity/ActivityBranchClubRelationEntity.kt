package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.kidedvisor.core.roomdb.dao.ActivityBranchClubRelationDao
import java.util.UUID

@Entity(
    tableName = ActivityBranchClubRelationDao.TABLE_NAME,
    primaryKeys = [
        "branch_id",
        "club_id"
    ]
)
data class ActivityBranchClubRelationEntity(
    @ColumnInfo(name = "branch_id")
    val branchId: UUID,
    @ColumnInfo(name = "club_id")
    val clubId: UUID
)
