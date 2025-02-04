package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch

interface GetPopularClubsUseCase {
    fun execute(): List<ClubInSearch>
}