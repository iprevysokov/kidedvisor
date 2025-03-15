package com.example.kidedvisor.club.di

import com.example.kidedvisor.club.domain.api.ClubInteractor
import com.example.kidedvisor.club.domain.impl.ClubInteractorImpl
import org.koin.dsl.module

val interactorClubModule = module {

    single<ClubInteractor> {
        ClubInteractorImpl(get())
    }
}