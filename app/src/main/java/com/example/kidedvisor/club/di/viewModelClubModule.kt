package com.example.kidedvisor.club.di

import com.example.kidedvisor.club.presentation.viewModel.ClubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelClubModule = module {

    viewModel<ClubViewModel> {
        ClubViewModel(get())
    }

}