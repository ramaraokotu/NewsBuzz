package com.mobile.newsbuzz.presentation.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.mobile.newsbuzz.domain.usecase.news.GetNewsUseCase
import com.mobile.newsbuzz.presentation.utils.NewsUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * NewsListViewModel is the view model for the news list screen. it handles the ui state
 * and get the news from the use case.
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsListUiState())
    val uiState = _uiState.asStateFlow()

    fun getNews(
        country: String? = null,
    ) {
        _uiState.update {
            it.copy(
                news =
                getNewsUseCase(
                    country = country
                ).map { pagingData ->
                    pagingData.map { news ->
                        news.toUiModel()
                    }
                }.cachedIn(viewModelScope)
            )
        }
    }


    init {
        getNews(
            country = uiState.value.newsCountry,
        )
    }
}
