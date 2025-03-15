package com.example.kidedvisor.club.di

import com.example.kidedvisor.club.data.ClubRepositoryImpl
import com.example.kidedvisor.club.domain.api.ClubRepository
import com.example.kidedvisor.club.domain.models.ClubConvertor
import org.koin.dsl.module

val repositoryClubModule = module {

    single<ClubRepository> {
        ClubRepositoryImpl(get(), get())
    }

    single { ClubConvertor() }

}