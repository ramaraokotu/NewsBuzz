package com.mobile.newsbuzz.data.network.dto

import com.google.gson.annotations.SerializedName
import com.mobile.newsbuzz.domain.model.News

/**
 * News response DTO [NewsResponseDto]
 */
data class NewsResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) {
    data class ArticleDto(
        @SerializedName("author")
        val author: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("source")
        val source: SourceDto?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("urlToImage")
        val urlToImage: String?
    ) {
        data class SourceDto(
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?
        )
    }

    companion object {
        /**
         * Mapping function to convert [ArticleDto] to [News]
         */
        fun ArticleDto.toNews() =
            News(
                author = author.orEmpty(),
                content = content.orEmpty(),
                description = description.orEmpty(),
                publishedAt = publishedAt.orEmpty(),
                source = source?.name.orEmpty(),
                title = title.orEmpty(),
                url = url.orEmpty(),
                imageUrl = urlToImage.orEmpty()
            )

        /**
         * Mapping function to convert [News] to [ArticleDto]
         */
        fun News.toNewsDto() =
            ArticleDto(
                author = author,
                content = content,
                description = description,
                publishedAt = publishedAt,
                source = ArticleDto.SourceDto(null, source),
                title = title,
                url = url,
                urlToImage = imageUrl
            )
    }
}
