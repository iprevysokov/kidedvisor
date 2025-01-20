package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

interface ZeroSearchRepository {
    suspend fun getClubsType(): Flow<List<String>>
    suspend fun getClubInType(): Flow<List<ClubInSearch>>
}