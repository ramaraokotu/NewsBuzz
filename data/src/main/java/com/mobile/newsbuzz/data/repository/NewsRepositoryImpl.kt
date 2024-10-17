package com.mobile.newsbuzz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobile.newsbuzz.data.network.NewsApi
import com.mobile.newsbuzz.data.paging.NewsPagingSource
import com.mobile.newsbuzz.domain.model.News
import com.mobile.newsbuzz.domain.repository.NewsRepository
import com.mobile.newsbuzz.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *  [NewsRepository] implementation for fetching news from the API [NewsApi]
 */

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(
        country: String?
    ): Flow<PagingData<News>> = Pager(
        config =
        PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newsApi,
                country = country,
            )
        }
    ).flow
}
