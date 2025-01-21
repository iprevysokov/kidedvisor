package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.search.domain.api.GetClubsInfoInteractor
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.presenter.models.OuterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSliderClubsUseCaseImp(
    private val interactor: GetClubsInfoInteractor
) : GetSliderClubsUseCase {

    override fun execute(): Flow<List<OuterModel>> = flow {
        val branches = interactor.getClubBranch()
        emit(branches.map {
            OuterModel(
                it, interactor.getClubInBranch(it)
            )
        })
    }
}