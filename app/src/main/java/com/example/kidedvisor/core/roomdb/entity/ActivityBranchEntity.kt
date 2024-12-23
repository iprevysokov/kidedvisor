package com.example.kidedvisor.core.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ActivityBranchDao
import java.util.UUID

@Entity(tableName = ActivityBranchDao.TABLE_NAME)
data class ActivityBranchEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
)