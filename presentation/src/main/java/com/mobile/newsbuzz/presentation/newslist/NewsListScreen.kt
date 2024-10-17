package com.mobile.newsbuzz.presentation.newslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobile.newsbuzz.presentation.R
import com.mobile.newsbuzz.presentation.common.components.EmptyStateComponent
import com.mobile.newsbuzz.presentation.components.NewsList
import com.mobile.newsbuzz.presentation.navigation.Destinations
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * NewsListScreen is the screen that displays the list of news.
 */

@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel(),
    onNewsClick: (url: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    NewsListScreenContent(
        modifier = modifier,
        uiState = uiState,
        onNewsClick = onNewsClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreenContent(
    uiState: NewsListUiState,
    modifier: Modifier = Modifier,
    onNewsClick: (url: String) -> Unit
) {
    val newsPaging = uiState.news?.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier
            .testTag(stringResource(R.string.news_list_screen))
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.top_bar_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }
    ) { innerPadding ->
        val pullToRefreshState = rememberPullToRefreshState()
        var isRefreshing by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val onRefresh: () -> Unit = {
            isRefreshing = true
            newsPaging?.refresh()
            coroutineScope.launch {
                delay(1500)
                isRefreshing = false
            }
        }

        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (newsPaging != null) {
                NewsList(
                    modifier = Modifier
                        .fillMaxSize(),
                    newsPaging = newsPaging,
                    onNewsClick = {
                        onNewsClick(it)
                    }
                )
            } else {
                EmptyStateComponent(
                    modifier = Modifier.align(Alignment.Center),
                    message = stringResource(R.string.no_news_available)
                )
            }
        }
    }
}

