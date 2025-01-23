package com.example.kidedvisor.search.presenter.models

import com.example.kidedvisor.search.domain.models.ClubInSearch

sealed class SearchStartRVItem {
    data class Header(val headerText: String) : SearchStartRVItem()
    data class Request(val requestText: String) : SearchStartRVItem()
    data class Club(val clubInSearch: ClubInSearch) : SearchStartRVItem()
}