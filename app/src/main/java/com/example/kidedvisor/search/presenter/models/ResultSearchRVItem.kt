package com.example.kidedvisor.search.presenter.models

import com.example.kidedvisor.search.domain.models.ClubInSearch

sealed class ResultSearchRVItem {
    data class AdResult(val clubInSearch: ClubInSearch) : ResultSearchRVItem()
    data class Result(val clubInSearch: ClubInSearch) : ResultSearchRVItem()
}