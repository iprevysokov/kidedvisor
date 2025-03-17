package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ClubDao
import java.util.UUID

@Entity(tableName = ClubDao.TABLE_NAME)
data class ClubEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val address: String = "",
    @ColumnInfo(name = "for_age")
    val forAge: String = "",
    val contacts: String = "",
    @ColumnInfo(name = "work_days")
    val workDays: String = "",
    @ColumnInfo(name = "work_time")
    val workTime: String = "",
    val price: String = "",
    val schedule: String = "",
    val description: String = "",
    val photo: Int,
    @ColumnInfo(name = "is_recommend")
    val isRecommend: Boolean = false,
)
