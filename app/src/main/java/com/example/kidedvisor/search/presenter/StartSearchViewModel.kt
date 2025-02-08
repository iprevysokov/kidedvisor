package com.example.kidedvisor.search.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kidedvisor.R
import com.example.kidedvisor.search.domain.api.GetPopularClubsUseCase
import com.example.kidedvisor.search.domain.api.GetPopularRequestUseCase
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartSearchViewModel(
    private val getPopularRequestUseCase: GetPopularRequestUseCase,
    private val getPopularClubsUseCase: GetPopularClubsUseCase,
) : ViewModel() {

    private var _startSearchState = MutableLiveData<SearchScreenState>()
    val startSearchState: LiveData<SearchScreenState> get() = _startSearchState

    init {
        renderStartSearch()
    }

    fun renderStartSearch() {
        val popularRequest = getPopularRequestUseCase.execute()
        val popularClubs = getPopularClubsUseCase.execute()

        val items = buildList<SearchStartRVItem> {
            if (popularRequest.isNotEmpty()) {
                this += SearchStartRVItem.Header(R.string.popular_request)
                this += popularRequest.map { SearchStartRVItem.Request(it) }
            }

            if (popularClubs.isNotEmpty()) {
                this += SearchStartRVItem.Header(R.string.popular_clubs)
                this += popularClubs.map { SearchStartRVItem.Club(it) }
            }
        }

        _startSearchState.value = SearchScreenState.StartSearchState(items)
    }
}