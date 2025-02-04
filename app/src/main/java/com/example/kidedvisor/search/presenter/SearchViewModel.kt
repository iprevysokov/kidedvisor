package com.example.kidedvisor.search.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.R
import com.example.kidedvisor.core.utils.debounce
import com.example.kidedvisor.search.domain.api.GetAdBannerUseCase
import com.example.kidedvisor.search.domain.api.GetPopularClubsUseCase
import com.example.kidedvisor.search.domain.api.GetPopularRequestUseCase
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.domain.api.SearchInteractor
import com.example.kidedvisor.search.domain.models.AdBanner
import com.example.kidedvisor.search.domain.models.ClubInSearch
import com.example.kidedvisor.search.presenter.models.ClubsSelection
import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSliderClubsUseCase: GetSliderClubsUseCase,
    private val getPopularRequestUseCase: GetPopularRequestUseCase,
    private val getPopularClubsUseCase: GetPopularClubsUseCase,
    private val getAdBannerUseCase: GetAdBannerUseCase,
    private val searchInteractor: SearchInteractor,
) : ViewModel() {

    private val state = MutableLiveData<SearchScreenState>()
    fun getState(): LiveData<SearchScreenState> = state

    init {
        renderZeroSearch()
    }

    private fun renderZeroSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            val adBanner = getAdBannerUseCase.execute()
            getSliderClubsUseCase.execute()
                .collect { clubsSelectionList ->
                    state.postValue(
                        zeroSearchToUi(adBanner, clubsSelectionList)
                    )
                }
        }
    }

    private fun zeroSearchToUi(
        adBanner: AdBanner, clubsSelectionList: List<ClubsSelection>
    ): SearchScreenState.ZeroSearchState {

        var branches = emptyList<String>()
        val items = buildList<ZeroSearchRVItem> {
            this += ZeroSearchRVItem.AdBannerItem(adBanner)
            clubsSelectionList.map {
                this += ZeroSearchRVItem.ClubSelectionItem(it)
                branches += it.branchName
            }

        }

        return SearchScreenState.ZeroSearchState(items, branches)
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

        state.value = SearchScreenState.StartSearchState(items)
    }

    private fun startSearch(expression: String) {
        if (expression.isNotEmpty()) {
            viewModelScope.launch {
                val adClub = searchInteractor.getAdClub()
                searchInteractor.getSearchResult()
                    .collect { clubs ->
                        state.postValue(castSearchResultToUi(adClub, clubs))
                    }

            }
        }
    }

    val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
            startSearch(changedText)
        }

    private fun castSearchResultToUi(
        adClub: ClubInSearch, clubs: List<ClubInSearch>
    ): SearchScreenState.ResultSearchState {
        val list = buildList<ResultSearchRVItem> {
            this += ResultSearchRVItem.AdResult(adClub)
            this += clubs.map { ResultSearchRVItem.Result(it) }
        }
        return SearchScreenState.ResultSearchState(list)
    }

    companion object{
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}