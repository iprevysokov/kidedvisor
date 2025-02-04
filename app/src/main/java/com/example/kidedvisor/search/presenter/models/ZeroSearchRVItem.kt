package com.example.kidedvisor.search.presenter.models

import com.example.kidedvisor.search.domain.models.AdBanner

sealed class ZeroSearchRVItem {
    data class AdBannerItem(val adBanner: AdBanner) : ZeroSearchRVItem()
    data class ClubSelectionItem(val clubsSelection: ClubsSelection) : ZeroSearchRVItem()
}