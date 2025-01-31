package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.search.domain.api.GetClubsInfoInteractor
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.presenter.models.ClubsSelection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSliderClubsUseCaseImpl(
    private val interactor: GetClubsInfoInteractor
) : GetSliderClubsUseCase {

    override fun execute(): Flow<List<ClubsSelection>> = flow {
        val branches = interactor.getClubBranch()
        emit(branches.map {
            ClubsSelection(
                it, interactor.getClubInBranch(it)
            )
        })
    }
}