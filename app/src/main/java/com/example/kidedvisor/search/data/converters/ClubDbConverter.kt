package com.example.kidedvisor.search.data.converters

import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.search.domain.models.ClubInSearch

class ClubDbConverter {

    fun mapActivityType(activityBranchEntity: ActivityBranchEntity): String {
        return activityBranchEntity.name
    }

    fun mapClubs(
        branch: String,
        clubEntity: ClubEntity
    ): ClubInSearch {
        return ClubInSearch(
            name = clubEntity.name,
            address = clubEntity.address,
            branch = branch,
            image = clubEntity.photo.toInt(),
            rating = 3
        )
    }
}