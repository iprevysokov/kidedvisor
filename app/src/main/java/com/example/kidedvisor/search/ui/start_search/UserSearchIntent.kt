package com.example.kidedvisor.search.ui.start_search

sealed class UserSearchIntent {
    data object StartSearchIntent : UserSearchIntent()
    data class ResultSearchIntent(val request: String) : UserSearchIntent()
}
