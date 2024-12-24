package com.example.kidedvisor.sample.di

import com.example.kidedvisor.sample.data.SampleCollectionRepositoryImpl
import com.example.kidedvisor.sample.domain.api.SampleCollectionRepository
import org.koin.dsl.module

val repositorySampleModule = module {
    single<SampleCollectionRepository> {
        SampleCollectionRepositoryImpl(get())
    }
}