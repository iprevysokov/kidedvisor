package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Insert
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity

interface ActivityBranchDao {

    @Insert
    suspend fun addActivityBranch(activityBranchEntity: ActivityBranchEntity)

    companion object {
        const val TABLE_NAME = "Activity_Branch"
    }
}