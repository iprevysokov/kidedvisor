package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

interface GetClubsInfoInteractor {
    suspend fun getClubBranch(): List<String>
    suspend fun getClubInBranch(branch: String): List<ClubInSearch>
}