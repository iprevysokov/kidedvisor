package com.example.kidedvisor.search.di

import com.example.kidedvisor.search.domain.api.GetClubsInfoInteractor
import com.example.kidedvisor.search.domain.api.GetSliderClubsUseCase
import com.example.kidedvisor.search.domain.impl.GetClubsInfoInteractorImpl
import com.example.kidedvisor.search.domain.impl.GetSliderClubsUseCaseImpl
import org.koin.dsl.module

val interactorSearchModule = module {

    factory<GetClubsInfoInteractor> {
        GetClubsInfoInteractorImpl(get())
    }

    factory<GetSliderClubsUseCase> {
        GetSliderClubsUseCaseImpl(get())
    }
}