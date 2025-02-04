package com.example.kidedvisor.search.presenter.models

import com.example.kidedvisor.search.domain.models.ClubInSearch

data class ClubsSelection(
    val branchName: String,
    val clubs: List<ClubInSearch>
)
