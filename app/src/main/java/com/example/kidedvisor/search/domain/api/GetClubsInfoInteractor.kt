package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

interface GetClubsInfoInteractor {
    suspend fun getClubTypes(): Flow<List<String>>
    suspend fun getClubInType(type: String): Flow<List<ClubInSearch>>
}