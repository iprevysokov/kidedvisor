package com.example.kidedvisor.search.di

import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.presenter.user_search.UserSearchViewModel
import com.example.kidedvisor.search.presenter.zero_search.ZeroSearchViewModel
import org.koin.dsl.module

val viewModelModule = module {

    factory<SearchViewModel> {
        SearchViewModel(get(), get(), get(), get(), get())
    }

    factory<ZeroSearchViewModel> {
        ZeroSearchViewModel(get(), get())
    }

    factory<UserSearchViewModel> {
        UserSearchViewModel(get(), get(), get())
    }
}