package com.mobile.newsbuzz.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.mobile.newsbuzz.presentation.R
import com.mobile.newsbuzz.presentation.utils.NewsUiModel
import com.mobile.newsbuzz.presentation.utils.toRelativeTime

/**
 * NewsItem Card Component to display news item
 */
@Composable
fun NewsItem(
    news: NewsUiModel,
    onNewsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = onNewsClick,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.news_card_rounded_corner)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.news_card_elevation)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceBright,
        )
    ) {
        Column {
            Row {
                NewsResourceHeaderImage(
                    headerImageUrl = news.imageUrl,
                    contentDescription = news.title,
                    modifier = modifier
                )
            }
            Box(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.image_bottom_space)),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.title_top_space)))
                    Row {
                        NewsResourceTitle(
                            news.title,
                            modifier = Modifier
                                .fillMaxWidth((.8f)),
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_height)))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NewsResourceMetaData(news.publishedAt, news.source)
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_height)))
                    NewsResourceShortDescription(news.content)
                }
            }
        }
    }
}

/**
 * News Resource Title
 */
@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(text = newsResourceTitle,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier)
}

/**
 * News Resource Meta Data
 */
@Composable
fun NewsResourceMetaData(publishedAt: String, resourceType: String) {
    val formattedDate = publishedAt.toRelativeTime()
    Text(
        if (resourceType.isNotBlank()) {
            stringResource(R.string.core_ui_card_meta_data_text, formattedDate, resourceType)
        } else {
            formattedDate
        },
        style = MaterialTheme.typography.labelSmall,
    )
}

/**
 * News Resource Short Description
 */
@Composable
fun NewsResourceShortDescription(
    newsResourceShortDescription: String,
) {
    Text(text = newsResourceShortDescription,
        style = MaterialTheme.typography.bodyLarge)
}
