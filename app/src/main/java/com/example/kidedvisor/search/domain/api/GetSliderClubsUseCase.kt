package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.presenter.models.OuterModel

interface GetSliderClubsUseCase {
    fun execute(): OuterModel
}