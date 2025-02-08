package com.example.kidedvisor.search.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.core.utils.debounce
import com.example.kidedvisor.search.domain.api.SearchInteractor
import com.example.kidedvisor.search.domain.models.ClubInSearch
import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem
import kotlinx.coroutines.launch

private const val SEARCH_DEBOUNCE_DELAY = 2000L

class ResultSearchViewModel(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var _resultSearchState = MutableLiveData<SearchScreenState>()
    val resultSearchState: LiveData<SearchScreenState> get() = _resultSearchState

    private fun startSearch(expression: String) {
        if (expression.isNotEmpty()) {
            viewModelScope.launch {
                val adClub = searchInteractor.getAdClub()
                searchInteractor.getSearchResult()
                    .collect { clubs ->
                        _resultSearchState.postValue(castSearchResultToUi(adClub, clubs))
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
}