package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kidedvisor.core.roomdb.dao.ProfileDao
import java.util.UUID

@Entity(tableName = ProfileDao.TABLE_NAME)
data class ProfileEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val midlName: String,
    val surname: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: Long,
    val email: String,
    val photo: String
)
