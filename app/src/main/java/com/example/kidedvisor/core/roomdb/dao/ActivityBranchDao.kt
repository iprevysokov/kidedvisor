package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity

interface ActivityBranchDao {

    @Insert
    suspend fun addActivityBranch(activityBranchEntity: ActivityBranchEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllActivityBranch(): ActivityBranchEntity?

    companion object {
        const val TABLE_NAME = "Activity_Branch"
    }
}