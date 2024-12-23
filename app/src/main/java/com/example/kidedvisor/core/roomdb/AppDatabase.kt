package com.example.kidedvisor.core.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kidedvisor.core.roomdb.dao.ActivityBranchDao
import com.example.kidedvisor.core.roomdb.dao.ActivityTypeDao
import com.example.kidedvisor.core.roomdb.dao.ClubDao
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.core.roomdb.entity.CommentEntity
import com.example.kidedvisor.core.roomdb.entity.ProfileEntity
import com.example.kidedvisor.core.roomdb.entity.ReviewEntity

@Database(
    version = 1, entities = [
        ActivityBranchEntity::class,
        ActivityTypeEntity::class,
        ClubEntity::class,
        ProfileEntity::class,
        ReviewEntity::class,
        CommentEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao
    abstract fun activityBranch(): ActivityBranchDao
    abstract fun activityType(): ActivityTypeDao
}