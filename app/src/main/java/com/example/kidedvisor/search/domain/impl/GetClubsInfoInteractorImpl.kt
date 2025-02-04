package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.search.domain.api.GetClubsInfoInteractor
import com.example.kidedvisor.search.domain.api.ZeroSearchRepository
import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow

class GetClubsInfoInteractorImpl(
        private val zeroSearchRepository: ZeroSearchRepository
) : GetClubsInfoInteractor {

    override suspend fun getClubBranch(): List<String> {
        return zeroSearchRepository.getClubsBranch()
    }

    override suspend fun getClubInBranch(branch: String): List<ClubInSearch> {
        return zeroSearchRepository.getClubInBranch(branch)
    }
}