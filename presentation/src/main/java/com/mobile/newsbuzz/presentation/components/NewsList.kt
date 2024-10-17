package com.mobile.newsbuzz.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mobile.newsbuzz.presentation.R
import com.mobile.newsbuzz.presentation.common.components.EmptyStateComponent
import com.mobile.newsbuzz.presentation.common.components.ErrorStateComponent
import com.mobile.newsbuzz.presentation.common.components.LoadingStateComponent
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import com.mobile.newsbuzz.presentation.utils.getPagingError

/**
 *  Handle the News List and display the news items in a LazyColumn
 *  and handle the loading and error states of the LazyColumn
 */

@Composable
fun NewsList(
    newsPaging: LazyPagingItems<NewsUiModel>,
    modifier: Modifier = Modifier,
    onNewsClick: (url: String) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.lazy_column_horizontal),
            vertical = dimensionResource(id = R.dimen.news_card_rounded_corner)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.vertical_space_arrangement))
    ) {
        items(newsPaging.itemCount) {
            val news = newsPaging[it]
            if (news != null) {
                NewsItem(
                    news = news,
                    onNewsClick = {
                        onNewsClick(news.url)
                    }
                )
            }
        }
        newsPaging.loadState.let { loadState ->
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingStateComponent()
                        }
                    }
                }

                loadState.refresh is LoadState.NotLoading && newsPaging.itemCount < 1 -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            EmptyStateComponent(
                                message = stringResource(R.string.no_news_available)
                            )
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorStateComponent(
                                message = error.getPagingError(context)
                            )
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier =
                                Modifier
                                    .size(dimensionResource(id = R.dimen.loading_progress_size))
                                    .align(Alignment.Center),
                                strokeWidth = dimensionResource(id = R.dimen.loading_progress_storke_width)
                            )
                        }
                    }
                }
            }
        }
    }
}
