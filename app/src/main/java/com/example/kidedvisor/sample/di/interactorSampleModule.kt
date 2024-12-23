package com.example.kidedvisor.sample.di

import com.example.kidedvisor.sample.domain.GetSampleDataUseCaseImpl
import com.example.kidedvisor.sample.domain.api.GetSampleDataUseCase
import org.koin.dsl.module

val interactorSampleModule = module {

    factory<GetSampleDataUseCase> {
        GetSampleDataUseCaseImpl(get())
    }
}