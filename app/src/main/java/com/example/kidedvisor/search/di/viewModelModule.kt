package com.example.kidedvisor.search.di

import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.presenter.StartSearchViewModel
import com.example.kidedvisor.search.presenter.ZeroSearchViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory<SearchViewModel> {
        SearchViewModel(get(), get(), get(), get(), get())
    }

    factory<ZeroSearchViewModel> {
        ZeroSearchViewModel(get(), get())
    }

    factory<StartSearchViewModel> {
        StartSearchViewModel(get(), get())
    }
}