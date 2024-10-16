package com.mobile.newsbuzz.presentation.newsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.newsbuzz.presentation.R
import com.mobile.newsbuzz.presentation.components.NewsResourceHeaderImage
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import com.mobile.newsbuzz.presentation.utils.toHumanReadableDateTIme

@Composable
fun NewsDetailsScreen(
    news: NewsUiModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    NewsDetailsScreenContent(
        modifier = modifier,
        news = news,
        onClickBack = {
            navController.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreenContent(
    news: NewsUiModel,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(id = R.string.back_icon)),
                        onClick = onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon)
                        )
                    }
                },
                title = {},
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {
                Text(
                    text =
                    if (news.author.isEmpty()) {
                        news.source
                    } else {
                        stringResource(R.string.by, news.author, news.source)
                    },
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
                Text(
                    text = news.publishedAt.toHumanReadableDateTIme(),
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
            }

            item {
                NewsResourceHeaderImage(
                    headerImageUrl = news.imageUrl,
                    contentDescription = news.title,
                    modifier = modifier
                )
            }

            item {
                Text(
                    text = news.description,
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
            }

            item {
                Text(
                    text = news.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
