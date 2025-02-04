package com.example.kidedvisor.sample.domain.api

import com.example.kidedvisor.search.domain.models.ClubInSearch

interface PopularSearchRepository {
    fun getPopularRequest(): List<String>
    fun getPopularClubs(): List<ClubInSearch>
}