package com.example.kidedvisor.search.presenter

sealed class UserSearchIntent {
    data object StartSearchIntent : UserSearchIntent()
    data class ResultSearchIntent(val request: String, val isDebounce: Boolean) : UserSearchIntent()
}
