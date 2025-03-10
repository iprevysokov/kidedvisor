package com.example.kidedvisor.search.presenter.user_search

import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

sealed class UserSearchState {
    data class StartSearchState(
        val searchStartRVItems: List<SearchStartRVItem>
    ) : UserSearchState()

    data class ResultSearchState(
        val resultSearchRVItem: List<ResultSearchRVItem>
    ) : UserSearchState()
}