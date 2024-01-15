package com.newsApp.domain.repositories.newsScreen

import androidx.paging.PagingData
import com.newsApp.domain.models.newsScreen.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(
        sources: List<String>,
    ): Flow<PagingData<ArticleEntity>>
}