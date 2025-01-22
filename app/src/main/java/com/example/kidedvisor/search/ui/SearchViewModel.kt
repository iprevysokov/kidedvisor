package com.example.kidedvisor.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSliderClubsUseCase: GetSliderClubsUseCase
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
}