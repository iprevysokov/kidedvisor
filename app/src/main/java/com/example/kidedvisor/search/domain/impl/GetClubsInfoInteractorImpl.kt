package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.search.domain.api.GetClubsInfoInteractor
import com.example.kidedvisor.search.domain.api.ZeroSearchRepository
import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

class GetClubsInfoInteractorImpl(
        private val zeroSearchRepository: ZeroSearchRepository
) : GetClubsInfoInteractor {

    override suspend fun getClubTypes(): Flow<List<String>> {
        return zeroSearchRepository.getClubsType()
    }

    override suspend fun getClubInType(type: String): Flow<List<ClubInSearch>> {
        return zeroSearchRepository.getClubInType()
    }
}