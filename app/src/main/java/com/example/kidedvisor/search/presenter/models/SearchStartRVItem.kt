package com.example.kidedvisor.search.presenter.models

import androidx.annotation.StringRes
import com.example.kidedvisor.search.domain.models.ClubInSearch

sealed class SearchStartRVItem {
    data class Header(@StringRes val headerText: Int) : SearchStartRVItem()
    data class Request(val requestList: List<String>) : SearchStartRVItem()
    data class Club(val clubList: List<ClubInSearch>) : SearchStartRVItem()
}