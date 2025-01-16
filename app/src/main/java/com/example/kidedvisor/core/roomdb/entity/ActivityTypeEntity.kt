package com.example.kidedvisor.core.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ActivityTypeDao
import java.util.UUID

@Entity(
    tableName = ActivityTypeDao.TABLE_NAME,
)
data class ActivityTypeEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String
)
