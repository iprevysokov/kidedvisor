package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
    suspend fun getAdClub(): ClubInSearch
    suspend fun getSearchResult(): Flow<List<ClubInSearch>>
}