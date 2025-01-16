package com.example.kidedvisor.core.roomdb.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity

data class ClubBranch(
    @Embedded val clubEntity: ClubEntity,
    @Relation(
        parentColumn = "club_id",
        entityColumn = "branch_id",
        associateBy = Junction(ActivityBranchClubRelationEntity::class)
    )
    val listActivityBranchEntity: List<ActivityBranchEntity>
)
