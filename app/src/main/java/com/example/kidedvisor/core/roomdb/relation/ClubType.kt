package com.example.kidedvisor.core.roomdb.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity

data class ClubType(
    @Embedded val clubEntity: ClubEntity,
    @Relation(
        parentColumn = "club_id",
        entityColumn = "type_id",
        associateBy = Junction(ActivityTypeClubRelationEntity::class)
    )
    val listActivityTypeEntity: List<ActivityTypeEntity>
)
