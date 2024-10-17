package com.mobile.newsbuzz.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.mobile.newsbuzz.presentation.R

/**
 * That displays a news resource header image.
 */
@Composable
fun NewsResourceHeaderImage(
    headerImageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = headerImageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.news_header_resource_image_height)),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            // Display a progress bar while loading
            CircularProgressIndicator(
                modifier = modifier
                    .align(Alignment.Center)
                    .size(dimensionResource(id = R.dimen.progress_bar_image_size)),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.news_header_resource_image_height)),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) {
                imageLoader
            } else {
                painterResource(id = R.drawable.core_designsystem_ic_placeholder_default)
            },
            contentDescription = contentDescription,
        )
    }
}
