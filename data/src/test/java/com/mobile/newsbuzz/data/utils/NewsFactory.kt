package com.mobile.newsbuzz.data.utils

import com.mobile.newsbuzz.domain.model.News
import java.util.concurrent.atomic.AtomicInteger

/**
 * A simple factory class that helps in creating fake data for testing.
 */
class NewsFactory {
    private val counter = AtomicInteger(0)

    fun createNews(): News {
        val id = counter.incrementAndGet()
        val news =
            News(
                author = "author $id",
                content = "content $id",
                description = "description $id",
                publishedAt = "publishedAt $id",
                source = "source $id",
                title = "title $id",
                url = "url $id",
                imageUrl = "imageUrl $id"
            )
        return news
    }
}
