package com.mobile.newsbuzz.data.network

import com.mobile.newsbuzz.data.network.dto.NewsResponseDto
import com.mobile.newsbuzz.data.network.dto.NewsResponseDto.Companion.toNewsDto
import com.mobile.newsbuzz.domain.model.News

/**
 * Fake implementation of the [NewsApi] interface
 */
class FakeNewsApi : NewsApi {
    private val news = mutableListOf<NewsResponseDto.ArticleDto>()
    var shouldThrowException = false

    fun addNews(news: List<News>) {
        this.news.addAll(news.map { it.toNewsDto() })
    }

    override suspend fun fetchNews(
        country: String?,
        pageSize: Int,
        page: Int,
        apiKey: String
    ): NewsResponseDto = if (shouldThrowException) {
        throw Exception("Fake exception")
    } else {
        NewsResponseDto(
            articles = news,
            status = "ok",
            totalResults = news.size
        )
    }
}
