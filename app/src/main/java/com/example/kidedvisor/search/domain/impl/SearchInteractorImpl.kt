package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.search.domain.api.ResultSearchRepository
import com.example.kidedvisor.search.domain.api.SearchInteractor
import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

class SearchInteractorImpl(
    private val repository: ResultSearchRepository
) : SearchInteractor {
    override suspend fun getAdClub(): ClubInSearch {
        return repository.getAdClub()
    }

    override suspend fun getSearchResult(): Flow<List<ClubInSearch>> {
        return repository.getSearchResult()
    }
}