package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity

@Dao
interface ActivityBranchDao {

    @Insert
    suspend fun addActivityBranch(activityBranchEntity: ActivityBranchEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllActivityBranch(): List<ActivityBranchEntity>

    @Query("SELECT * FROM ${ActivityTypeDao.TABLE_NAME} WHERE name = :name")
    suspend fun getActivityBranchByName(name: String): ActivityBranchEntity

    companion object {
        const val TABLE_NAME = "Activity_Branch"
    }
}