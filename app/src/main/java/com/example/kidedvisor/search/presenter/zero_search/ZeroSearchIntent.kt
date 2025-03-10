package com.example.kidedvisor.search.presenter.zero_search

sealed class ZeroSearchIntent {
    data object ZeroSearch : ZeroSearchIntent()
}