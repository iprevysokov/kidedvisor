package com.example.kidedvisor.search.presenter.zero_search

import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem

sealed class ZeroSearchState {
    data class ZeroSearch(val zeroSearchRVItem: List<ZeroSearchRVItem>,
                          val branches: List<String>) : ZeroSearchState()
}