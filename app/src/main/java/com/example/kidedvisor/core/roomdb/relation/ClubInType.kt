package com.example.kidedvisor.core.roomdb.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity

data class ClubInType(
    @Embedded val activityTypeEntity: ActivityTypeEntity,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "club_id",
        associateBy = Junction(ActivityTypeClubRelationEntity::class)
    )
    val listClubEntity: List<ClubEntity>
)
