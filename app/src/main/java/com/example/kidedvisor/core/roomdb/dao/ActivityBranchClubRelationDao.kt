package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchClubRelationEntity
import java.util.UUID

@Dao
interface ActivityBranchClubRelationDao {

    companion object {
        const val TABLE_NAME = "Activity_Branch_Club_Relation"
    }

    @Insert
    suspend fun addActivityBranchClubRelation(
        activityBranchClubRelationEntity: ActivityBranchClubRelationEntity
    )

    @Query("SELECT club_id FROM $TABLE_NAME WHERE branch_id = :branch")
    suspend fun getClubId(branch: UUID): List<UUID>
}