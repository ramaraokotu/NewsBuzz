package com.mobile.newsbuzz.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobile.newsbuzz.presentation.R

/**
 * Empty state component for displaying a message when there are no news available.
 */
@Composable
fun EmptyStateComponent(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.no_news_available)
) {
    Column(
        modifier = modifier.testTag(stringResource(R.string.empty_state_component)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.vertical_arrangement))
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
