package com.example.kidedvisor.search.presenter.models

import com.example.kidedvisor.search.domain.models.ClubInSearch

data class OuterModel(
    val branchName: String,
    val clubs: List<ClubInSearch>
)
