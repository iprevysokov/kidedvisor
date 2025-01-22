package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

interface ZeroSearchRepository {
    suspend fun getClubsBranch(): List<String>
    suspend fun getClubInBranch(branch: String): List<ClubInSearch>
}