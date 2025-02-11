package com.example.kidedvisor.search.presenter.zero_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.search.domain.api.GetAdBannerUseCase
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.domain.models.AdBanner
import com.example.kidedvisor.search.presenter.SearchScreenState
import com.example.kidedvisor.search.presenter.models.ClubsSelection
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZeroSearchViewModel(
    private val getAdBannerUseCase: GetAdBannerUseCase,
    private val getSliderClubsUseCase: GetSliderClubsUseCase,
) : ViewModel() {

    private val _zeroSearchState = MutableLiveData<SearchScreenState>()
    val zeroSearchState: LiveData<SearchScreenState> get() = _zeroSearchState

    init {
        renderZeroSearch()
    }

    fun renderZeroSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            val adBanner = getAdBannerUseCase.execute()
            getSliderClubsUseCase.execute()
                .collect { clubsSelectionList ->
                    _zeroSearchState.postValue(
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
}