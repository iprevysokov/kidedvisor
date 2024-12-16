package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Insert
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity

interface ActivityTypeDao {

    @Insert
    suspend fun addActivityType(activityTypeEntity: ActivityTypeEntity)

    companion object {
        const val TABLE_NAME = "Activity_Type"
    }
}