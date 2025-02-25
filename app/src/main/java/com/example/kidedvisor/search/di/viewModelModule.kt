package com.example.kidedvisor.search.di

import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.presenter.filters.FiltersViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory<SearchViewModel> {
        SearchViewModel(get(), get(), get(), get())
    }

    factory<FiltersViewModel> {
        FiltersViewModel()
    }
}