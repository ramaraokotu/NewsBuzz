package com.mobile.newsbuzz.domain.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a news article.
 */
@Serializable
data class News(
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val publishedAt: String,
    val author: String,
    val url: String
)
