package com.mobile.newsbuzz.domain.usecase.news

import com.mobile.newsbuzz.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for getting news from the repository.
 */
class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        country: String?,
    ) = repository.getNews(
        country = country
    )
}
