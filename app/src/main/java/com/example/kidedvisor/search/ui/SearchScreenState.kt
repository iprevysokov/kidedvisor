package com.example.kidedvisor.search.ui

import com.example.kidedvisor.search.presenter.models.OuterModel

sealed class SearchScreenState {
    data class ZeroSearchState(
//        val filterTags: List<String>,
        val outerModels: List<OuterModel>
    ) : SearchScreenState()
}