package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.dao.ActivityTypeDao.Companion.TABLE_NAME
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity

@Dao
interface ActivityTypeDao {

    @Insert
    suspend fun addActivityType(activityTypeEntity: ActivityTypeEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllActivityType(): List<ActivityTypeEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE name = :name")
    suspend fun getActivityTypeByName(name: String): ActivityTypeEntity

    companion object {
        const val TABLE_NAME = "Activity_Type"
    }
}