package com.mobile.newsbuzz.domain.usecase

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.mobile.newsbuzz.domain.model.News
import com.mobile.newsbuzz.domain.repository.NewsRepository
import com.mobile.newsbuzz.domain.usecase.news.GetNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [GetNewsUseCase].
 */
class GetNewsUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: NewsRepository

    private lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getNewsUseCase = GetNewsUseCase(repository)
    }

    @Test
    fun `get news from repository`() =
        runBlocking {
            // Given
            val news = sampleNews()
            coEvery { repository.getNews(any()) } returns flowOf(news)

            // When
            val result = getNewsUseCase(null).first()

            // Then
            assertThat(result).isEqualTo(news)
            coVerify { repository.getNews(any()) }
        }

    private fun sampleNews(): PagingData<News> = PagingData.from(
        listOf(
            News(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "urlToImage",
                publishedAt = "publishedAt",
                content = "content",
                source = "source",
                author = "author"
            )
        )
    )
}
