package com.example.kidedvisor.sample.di

import com.example.kidedvisor.sample.data.SampleCollectionRepository
import org.koin.dsl.module

val repositoryCoreModule = module {
    single<SampleCollectionRepository> {
        SampleCollectionRepository(get())
    }
}