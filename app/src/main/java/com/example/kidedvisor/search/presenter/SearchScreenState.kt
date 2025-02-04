package com.example.kidedvisor.search.presenter

import com.example.kidedvisor.search.presenter.models.SearchStartRVItem
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem

sealed class SearchScreenState {

    data class ZeroSearchState(
        val zeroSearchRVItem: List<ZeroSearchRVItem>,
        val branches: List<String>
    ) : SearchScreenState()

    data class StartSearchState(
        val searchStartRVItems: List<SearchStartRVItem>
    ) : SearchScreenState()
}