package com.example.kidedvisor.search.data

import com.example.kidedvisor.core.roomdb.AppDatabase
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.search.data.converters.ClubDbConverter
import com.example.kidedvisor.search.domain.api.ZeroSearchRepository
import com.example.kidedvisor.search.domain.models.ClubInSearch

class ZeroSearchRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val clubDbConverter: ClubDbConverter
) : ZeroSearchRepository {

    override suspend fun getClubsBranch(): List<String> {
        val activityBranches = appDatabase.activityBranchDao().getAllActivityBranch()
        return convertFromActivityBranchEntity(activityBranches)
    }

    override suspend fun getClubInBranch(branch: String): List<ClubInSearch> {
        val branchEntity = appDatabase.activityBranchDao().getActivityBranchByName(branch)
        val clubsId = appDatabase.activityBranchClubRelationDao().getClubId(branchEntity.id)
        val clubsEntity = appDatabase.clubDao().getClubsById(clubsId)
        return convertToClubSearch(branchEntity.name, clubsEntity)

    }

    private fun convertFromActivityBranchEntity(activityBranches: List<ActivityBranchEntity>): List<String> {
        return activityBranches.map { clubDbConverter.mapActivityType(it) }
    }

    private fun convertToClubSearch(
        branch: String,
        clubsEntity: List<ClubEntity>
    ): List<ClubInSearch> {
        return clubsEntity.map { clubDbConverter.mapClubs(branch, it) }
    }
}