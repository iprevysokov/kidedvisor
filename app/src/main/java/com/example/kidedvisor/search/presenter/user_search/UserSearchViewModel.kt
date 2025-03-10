package com.example.kidedvisor.search.presenter.user_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.R
import com.example.kidedvisor.core.utils.debounce
import com.example.kidedvisor.search.domain.api.GetPopularClubsUseCase
import com.example.kidedvisor.search.domain.api.GetPopularRequestUseCase
import com.example.kidedvisor.search.domain.api.SearchInteractor
import com.example.kidedvisor.search.domain.models.ClubInSearch
import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem
import kotlinx.coroutines.launch

class UserSearchViewModel(
    private val getPopularRequestUseCase: GetPopularRequestUseCase,
    private val getPopularClubsUseCase: GetPopularClubsUseCase,
    private val searchInteractor: SearchInteractor,
) : ViewModel() {

    private var _userSearchState = MutableLiveData<UserSearchState>()
    val userSearchState: LiveData<UserSearchState> get() = _userSearchState

    init {
        processIntent(UserSearchIntent.StartSearchIntent)
    }

    private fun runStartSearch() {
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

        _userSearchState.value = UserSearchState.StartSearchState(items)
    }

    private fun runResultSearch(expression: String, isDebounce: Boolean) {
        if (isDebounce) searchDebounce(expression)
        else sendRequest(expression)
    }

    private fun sendRequest(expression: String) {
        if (expression.isNotEmpty()) {
            viewModelScope.launch {
                val adClub = searchInteractor.getAdClub()
                searchInteractor.getSearchResult()
                    .collect { clubs ->
                        _userSearchState.postValue(
                            castSearchResultToUi(adClub, clubs)
                        )
                    }
            }
        }
    }

    private fun castSearchResultToUi(
        adClub: ClubInSearch, clubs: List<ClubInSearch>
    ): UserSearchState.ResultSearchState {
        val list = buildList<ResultSearchRVItem> {
            this += ResultSearchRVItem.AdResult(adClub)
            this += clubs.map { ResultSearchRVItem.Result(it) }
        }
        return UserSearchState.ResultSearchState(list)
    }


    fun processIntent(intent: UserSearchIntent) {
        when (intent) {
            is UserSearchIntent.ResultSearchIntent -> runResultSearch(intent.request, intent.isDebounce)
            UserSearchIntent.StartSearchIntent -> runStartSearch()
        }
    }

    private val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY, viewModelScope, true) { changedText ->
            sendRequest(changedText)
        }

    companion object{
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }
}