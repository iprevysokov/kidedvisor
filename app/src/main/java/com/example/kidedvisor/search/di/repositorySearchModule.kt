package com.example.kidedvisor.search.di

import com.example.kidedvisor.search.data.ResultSearchRepositoryImpl
import com.example.kidedvisor.search.data.ZeroSearchRepositoryImpl
import com.example.kidedvisor.search.data.converters.ClubDbConverter
import com.example.kidedvisor.search.domain.api.ResultSearchRepository
import com.example.kidedvisor.search.domain.api.ZeroSearchRepository
import org.koin.dsl.module

val repositorySearchModule = module {

    single<ZeroSearchRepository> {
        ZeroSearchRepositoryImpl(get(), get())
    }

    factory {
        ClubDbConverter()
    }

    single<ResultSearchRepository> {
        ResultSearchRepositoryImpl(get(), get())
    }
}