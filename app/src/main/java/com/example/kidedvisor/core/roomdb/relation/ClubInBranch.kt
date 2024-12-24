package com.example.kidedvisor.core.roomdb.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity

data class ClubInBranch(
    @Embedded val activityBranchEntity: ActivityBranchEntity,
    @Relation(
        parentColumn = "branch_id",
        entityColumn = "club_id",
        associateBy = Junction(ActivityBranchClubRelationEntity::class)
    )
    val listClubEntity: List<ClubEntity>
)
