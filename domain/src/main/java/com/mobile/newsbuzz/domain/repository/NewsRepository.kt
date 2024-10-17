package com.mobile.newsbuzz.domain.repository

import com.mobile.newsbuzz.domain.model.News
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

/**
 * Repository interface for News.
 */
interface NewsRepository {
    fun getNews(
        country: String?
    ): Flow<PagingData<News>>
}
