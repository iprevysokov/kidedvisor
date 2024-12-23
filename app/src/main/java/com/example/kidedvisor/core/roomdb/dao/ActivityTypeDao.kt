package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity

@Dao
interface ActivityTypeDao {

    @Insert
    suspend fun addActivityType(activityTypeEntity: ActivityTypeEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllActivityType(): ActivityTypeEntity?

    companion object {
        const val TABLE_NAME = "Activity_Type"
    }
}