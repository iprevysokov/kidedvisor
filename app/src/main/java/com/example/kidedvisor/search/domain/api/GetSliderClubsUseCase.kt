package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.presenter.models.OuterModel
import kotlinx.coroutines.flow.Flow

interface GetSliderClubsUseCase {
    fun execute(): Flow<List<OuterModel>>
}