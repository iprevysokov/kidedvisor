package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchClubRelationEntity

@Dao
interface ActivityBranchClubRelationDao {
    @Insert
    suspend fun addActivityBranchClubRelation(activityBranchClubRelationEntity: ActivityBranchClubRelationEntity)

    companion object {
        const val TABLE_NAME = "Activity_Branch_Club_Relation"
    }
}