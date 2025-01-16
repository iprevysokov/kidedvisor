package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeClubRelationEntity

@Dao
interface ActivityTypeClubRelationDao {
    @Insert
    suspend fun addActivityTypeClubRelation(activityTypeClubRelationEntity: ActivityTypeClubRelationEntity)

    companion object {
        const val TABLE_NAME = "Activity_Type_Club_Relation"
    }
}