package com.example.kidedvisor.search.ui

import com.example.kidedvisor.search.presenter.models.OuterModel
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

sealed class SearchScreenState {

    data class ZeroSearchState(
        val outerModels: List<OuterModel>
    ) : SearchScreenState()

    data class StartSearchState(val searchStartRVItem: List<SearchStartRVItem>) : SearchScreenState()
}