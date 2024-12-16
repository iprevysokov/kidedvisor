package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class ClubEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    @ColumnInfo(name = "activity_type")
    val activityType: UUID,
    val address: String,
    @ColumnInfo(name = "for_age")
    val forAge: String,
    val contacts: String,
    @ColumnInfo(name = "work_days")
    val workDays: String,
    @ColumnInfo(name = "work_time")
    val workTime: String,
    val price: String,
    val schedule: String,
    val description: String,
    val photo: String
)
