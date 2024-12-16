package com.example.kidedvisor.core.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
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
