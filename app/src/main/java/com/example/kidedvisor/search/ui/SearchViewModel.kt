package com.example.kidedvisor.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.R
import com.example.kidedvisor.search.domain.api.GetPopularClubsUseCase
import com.example.kidedvisor.search.domain.api.GetPopularRequestUseCase
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSliderClubsUseCase: GetSliderClubsUseCase,
    private val getPopularRequestUseCase: GetPopularRequestUseCase,
    private val getPopularClubsUseCase: GetPopularClubsUseCase,
) : ViewModel() {

    private val state = MutableLiveData<SearchScreenState>()
    fun getState(): LiveData<SearchScreenState> = state

    init {
        renderZeroSearch()
    }

    private fun renderZeroSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            getSliderClubsUseCase.execute()
                .collect { outerModel ->
                    state.postValue(
                        SearchScreenState.ZeroSearchState(outerModel)
                    )
                }
        }
    }

    private fun renderStartSearch() {
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

        SearchScreenState.StartSearchState(items)
    }
}