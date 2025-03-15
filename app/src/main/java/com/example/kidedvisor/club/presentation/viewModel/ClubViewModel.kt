package com.example.kidedvisor.club.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kidedvisor.club.domain.api.ClubInteractor
import com.example.kidedvisor.club.domain.models.ClubIntent
import com.example.kidedvisor.club.domain.models.ClubState
import com.example.kidedvisor.club.domain.models.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.UUID

class ClubViewModel(private val clubInteractor: ClubInteractor) : ViewModel() {

    private val _clubState = MutableLiveData<ClubState>(ClubState.Loading)
    val clubState: LiveData<ClubState> get() = _clubState

    fun processIntent(intent: ClubIntent) {
        when (intent) {
            is ClubIntent.LoadClub -> loadClub(intent.id)
        }
    }

    private fun loadClub(id: UUID) {
        _clubState.value = ClubState.Loading

        viewModelScope.launch {
            clubInteractor.getClubById(id)
                .catch { e ->
                    _clubState.postValue(ClubState.Error(e.message ?: "Unknown error"))
                }
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> resource.data?.let {
                            _clubState.postValue(ClubState.Success(it))
                        }

                        is Resource.Error ->
                            _clubState.postValue(ClubState.Error(resource.message.toString()))
                    }
                }
        }
    }

}