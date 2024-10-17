
package com.mobile.newsbuzz.presentation.newslist

import androidx.paging.PagingData
import com.mobile.newsbuzz.presentation.utils.Constants
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import kotlinx.coroutines.flow.Flow

data class NewsListUiState(
    val news: Flow<PagingData<NewsUiModel>>? = null,
    val showCountryDialog: Boolean = false,
    val newsCountry: String = Constants.COUNTRY_CODE,
)
