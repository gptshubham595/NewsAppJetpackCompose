package com.newsApp.core.repositories.newsSource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.newsApp.data.remote.api.NewsApi
import com.newsApp.domain.models.newsScreen.ArticleEntity
import com.newsApp.domain.repositories.newsScreen.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<ArticleEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}