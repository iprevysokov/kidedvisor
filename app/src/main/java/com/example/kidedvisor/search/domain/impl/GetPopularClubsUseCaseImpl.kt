package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.sample.domain.api.PopularSearchRepository
import com.example.kidedvisor.search.domain.api.GetPopularClubsUseCase
import com.example.kidedvisor.search.domain.models.ClubInSearch

class GetPopularClubsUseCaseImpl(
    private val repository: PopularSearchRepository
) : GetPopularClubsUseCase {

    override fun execute(): List<ClubInSearch> {
        return repository.getPopularClubs()
    }

}