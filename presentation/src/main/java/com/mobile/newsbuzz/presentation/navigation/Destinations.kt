package com.mobile.newsbuzz.presentation.navigation

import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    object NewsList

    @Serializable
    data class NewsDetails(val news: NewsUiModel)
}
