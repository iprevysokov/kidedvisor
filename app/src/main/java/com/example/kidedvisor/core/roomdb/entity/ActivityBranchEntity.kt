package com.example.kidedvisor.core.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "Activity_Branch")
data class ActivityBranchEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
)