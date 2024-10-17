package com.mobile.newsbuzz.presentation.newslist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import com.mobile.newsbuzz.presentation.R
import com.mobile.newsbuzz.presentation.common.theme.NewsBuzzTheme
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class NewsListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showLoadingState_whenNewsListIsLoading() {
        /**
         * Paging library needs to determine the actual state of the data. When you
         * create an empty PagingData, it doesn't trigger a fetch, hence it remains
         * in a loading state.
         */
        val loadingNews = PagingData.empty<NewsUiModel>()

        composeTestRule.setContent {
            NewsBuzzTheme {
                NewsListScreenContent(
                    uiState = NewsListUiState(news = flowOf(loadingNews)),
                    onNewsClick = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.loading_state_component))
            .assertIsDisplayed()
    }

    @Test
    fun showEmptyState_whenNewsListIsEmpty() {
        composeTestRule.setContent {
            NewsBuzzTheme {
                NewsListScreenContent(
                    uiState = NewsListUiState(),
                    onNewsClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.no_news_available))
            .assertIsDisplayed()
    }

    @Test
    fun showNewsList_whenNewsListIsNotEmpty() {
        val newsList = listOf(
            NewsUiModel(
                title = "Title 1",
                description = "Description 1",
                imageUrl = "https://example.com/image1.jpg",
                url = "https://example.com/news1",
                source = "Source 1",
                publishedAt = "2021-09-01T00:00:00Z",
                content = "Content 1",
                author = "Author 1"
            ),
            NewsUiModel(
                title = "Title 2",
                description = "Description 2",
                imageUrl = "https://example.com/image2.jpg",
                url = "https://example.com/news2",
                source = "Source 2",
                publishedAt = "2021-09-02T00:00:00Z",
                content = "Content 2",
                author = "Author 2"
            )
        )

        val news = PagingData.from(newsList)

        composeTestRule.setContent {
            NewsBuzzTheme {
                NewsListScreenContent(
                    uiState = NewsListUiState(news = flowOf(news)),
                    onNewsClick = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Title 2").assertIsDisplayed()
    }
}
