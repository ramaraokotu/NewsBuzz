package com.mobile.newsbuzz.presentation.newslist

import com.mobile.newsbuzz.presentation.utils.NewsUiModel

sealed interface NewsListUiAction {
    data class NavigateToNewsDetails(val news: NewsUiModel) : NewsListUiAction
}
